package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord : String
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    val score : Int
        get() = _score
    val currentScrambledWord: String
        get() = _currentScrambledWord

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()

        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord() {
        currentWord = allWordsList.random();

        if (wordsList.contains(currentWord)) {
            getNextWord();
        }

        val shuffledWord = currentWord.toCharArray();

        while (String(shuffledWord).equals(currentWord, false)) {
            shuffledWord.shuffle()
        }

        _currentScrambledWord = String(shuffledWord)
        ++_currentWordCount
        wordsList.add(currentWord);
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord : String) : Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()

            return true
        }

        return false
    }
}