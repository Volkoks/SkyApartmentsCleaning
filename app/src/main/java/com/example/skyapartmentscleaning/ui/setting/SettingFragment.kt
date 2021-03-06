package com.example.skyapartmentscleaning.ui.setting

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.databinding.SettingFragmentBinding
import javax.inject.Inject

class SettingFragment : Fragment(R.layout.setting_fragment) {

    companion object {
        fun newInstance() = SettingFragment()
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SettingViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)
    }
    private var binding: SettingFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = SettingFragmentBinding.bind(view)
        stateSwitchTheme()

        binding?.switchSelectTheme?.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                viewModel.themeState = DARK_THEME
                saveStateSwitchTheme(true)
                saveThemeAndRecreate()
            } else {
                viewModel.themeState = LIGHT_THEME
                saveStateSwitchTheme(false)
                saveThemeAndRecreate()
            }

        }

    }

    private fun stateSwitchTheme() {
        when (getStateSwitch()) {
            true -> binding?.switchSelectTheme?.isChecked = true
            else -> binding?.switchSelectTheme?.isChecked = false
        }
    }

    private fun getStateSwitch(): Boolean? =
        context?.getSharedPreferences(TAG_STATE_SWITCH_THEME_SETTING, Context.MODE_PRIVATE)
            ?.getBoolean(TAG_SWITCH_THEME_SETTING_KEY, false)


    private fun saveThemeAndRecreate() {
        context?.getSharedPreferences(MY_SETTING, Context.MODE_PRIVATE)?.edit() {
            viewModel.themeState?.let { this.putInt(THEME_KEY, it) }
        }

        activity?.recreate()
    }

    private fun saveStateSwitchTheme(state: Boolean) {
        context?.getSharedPreferences(TAG_STATE_SWITCH_THEME_SETTING, Context.MODE_PRIVATE)
            ?.edit() {
                this.putBoolean(TAG_SWITCH_THEME_SETTING_KEY, state)
            }
    }
}