/**
 * Created by Ilia Shelkovenko on 09.08.2020.
 */

package ru.skillbranch.devintensive.models

class Bender(var status : Status = Status.NORMAL,
             var question : Question = Question.NAME) {

    fun askQuestion() : String = when(question) {
                Question.NAME -> Question.NAME.question
                Question.PROFESSION -> Question.PROFESSION.question
                Question.MATERIAL -> Question.MATERIAL.question
                Question.BDAY -> Question.BDAY.question
                Question.SERIAL -> Question.SERIAL.question
                Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>{
        if(validate(answer)) {
            return if (question.answers.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color

            } else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }else {
            return correctQuestion() to status.color
        }
    }
    private fun validate(answer: String): Boolean {
        return when(question) {
            Question.NAME -> answer == answer.capitalize()
            Question.PROFESSION -> answer == "${answer.first().toLowerCase()}${answer.substring(1, answer.length)}"
            Question.MATERIAL -> (answer.matches(Regex("\\D+")))
            Question.BDAY -> answer.matches(Regex("\\d+"))
            Question.SERIAL -> answer.matches(Regex("^[\\d]{7}\$"))
            else -> false
        }
    }

    private fun correctQuestion(): String {
        return when(question) {
            Question.NAME -> "Имя должно начинаться с заглавной буквы\n${question.question}"
            Question.PROFESSION -> "Профессия должна начинаться со строчной буквы\n${question.question}"
            Question.MATERIAL -> "Материал не должен содержать цифр\n${question.question}"
            Question.BDAY -> "Год моего рождения должен содержать только цифры\n${question.question}"
            Question.SERIAL -> "Серийный номер содержит только цифры, и их 7\n${question.question}"
            else -> "На этом все, вопросов больше нет"
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus() : Status {
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String> ) {
        NAME("Как меня зовут?", listOf("бендер","bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл","дерево","metal","iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion() : Question
    }
}