package com.chobo.shareddb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chobo.shareddb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // binding 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        //액티비티가 처음 실행될때 한번 수행하는 곳 (초기화)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData() // edit text 저장되어있던 값을 setText
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        binding.etHello.setText(pref.getString("name","입력값 없음")) //1번째 인자에서는 저장할 당시의 키 값을 적어줌 , 2번째는 데이터가 존재하지 않을경우 대처값

    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit() //수정모드
        edit.putString("name", binding.etHello.text.toString()) //1번째 인자에는 키 값, 2번째 인자에는 실제 값
        edit.apply() // 값을 저장
    }

    // 액티비티가 종료되는 시점이 다가올때 호출해서 edit text(id)값을 저장해버림
    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }


}

