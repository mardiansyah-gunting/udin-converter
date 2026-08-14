package com.udin.converter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.udin.converter.databinding.ActivityMainBinding
import com.udin.converter.databinding.DialogAppreciationBinding
import com.udin.converter.databinding.DialogHintBinding
import com.udin.converter.databinding.ItemHintTipBinding
import java.text.DecimalFormat
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_HEIGHT_RESULT = "height_result"
    }

    private lateinit var binding: ActivityMainBinding
    private val formatter = DecimalFormat("#.##")
    private var lastCalculatedHeight: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputDistance.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        binding.inputTopAngle.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        binding.inputBottomAngle.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.btnCalculate.setOnClickListener { calculateHeight() }

        binding.profileImage.setOnClickListener { showAppreciationDialog() }

        binding.btnToggleHint.setOnClickListener { showHintDialog() }

        binding.btnLanguage.setOnClickListener { showLanguageMenu() }

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_HEIGHT_RESULT)) {
            val height = savedInstanceState.getDouble(KEY_HEIGHT_RESULT)
            lastCalculatedHeight = height
            binding.textResult.text = getString(R.string.result_value_format, formatter.format(height))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        lastCalculatedHeight?.let { outState.putDouble(KEY_HEIGHT_RESULT, it) }
    }

    private fun showLanguageMenu() {
        val options = arrayOf(getString(R.string.lang_option_id), getString(R.string.lang_option_en))
        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_UdinConverter_ProfileDialog)
            .setTitle(R.string.language_switcher_label)
            .setItems(options) { _, index ->
                val languageTag = if (index == 0) "id" else "en"
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageTag))
            }
            .show()
    }

    private fun showAppreciationDialog() {
        binding.profileImage.visibility = View.INVISIBLE

        val dialogBinding = DialogAppreciationBinding.inflate(layoutInflater)
        dialogBinding.btnLinkedin.setOnClickListener {
            val url = getString(R.string.appreciation_linkedin_url)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_UdinConverter_ProfileDialog)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.dialog_close) { dialog, _ -> dialog.dismiss() }
            .setOnDismissListener { binding.profileImage.visibility = View.VISIBLE }
            .show()
    }

    private fun showHintDialog() {
        val dialogBinding = DialogHintBinding.inflate(layoutInflater)
        populateHintTips(dialogBinding)

        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_UdinConverter_ProfileDialog)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.dialog_close) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun populateHintTips(dialogBinding: DialogHintBinding) {
        val icons = resources.getStringArray(R.array.hint_tip_icons)
        val titles = resources.getStringArray(R.array.hint_tip_titles)
        val descriptions = resources.getStringArray(R.array.hint_tip_descriptions)

        icons.indices.forEach { i ->
            val item = ItemHintTipBinding.inflate(layoutInflater, dialogBinding.hintTipList, false)
            item.tipIcon.text = icons[i]
            item.tipTitle.text = titles[i]
            item.tipDescription.text = descriptions[i]
            dialogBinding.hintTipList.addView(item.root)
        }
    }

    private fun calculateHeight() {
        val distance = parseDouble(binding.inputDistance.text.toString())
        val topAngle = parseDouble(binding.inputTopAngle.text.toString())
        val bottomAngle = parseDouble(binding.inputBottomAngle.text.toString())

        if (distance == null || distance <= 0) {
            showError(getString(R.string.error_invalid_distance))
            return
        }
        if (topAngle == null) {
            showError(getString(R.string.error_invalid_top_angle))
            return
        }
        if (bottomAngle == null) {
            showError(getString(R.string.error_invalid_bottom_angle))
            return
        }

        val height = distance * (tan(Math.toRadians(topAngle)) - tan(Math.toRadians(bottomAngle)))
        lastCalculatedHeight = height
        binding.textResult.text = getString(R.string.result_value_format, formatter.format(height))
    }

    private fun parseDouble(value: String): Double? =
        value.trim().toDoubleOrNull()

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
