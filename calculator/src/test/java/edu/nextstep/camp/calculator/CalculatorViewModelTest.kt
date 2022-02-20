package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var calculatorViewModel: CalculatorViewModel

    @Before
    fun setUp() {
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun `수식이 비어있는 상태에서 숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)

        // when
        // then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1")
    }

    @Test
    fun `숫자가 입력된 상태에서 연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)

        // when
        calculatorViewModel.addToExpression(Operator.Plus)

        // then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1 +")
    }

    @Test
    fun `수식이 비어있지 않은 상태에서 지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)

        // when
        calculatorViewModel.removeLast()

        // then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("")
    }

    @Test
    fun `계산가능한 수식 상태에서 계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        // when
        calculatorViewModel.calculate()

        // then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("3")
    }
}
