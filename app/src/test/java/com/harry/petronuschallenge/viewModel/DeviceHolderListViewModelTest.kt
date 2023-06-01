package com.harry.petronuschallenge.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harry.petronuschallenge.data.model.Customer
import com.harry.petronuschallenge.data.model.DeviceHolder
import com.harry.petronuschallenge.repository.DeviceHolderListRepository
import com.harry.petronuschallenge.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeviceHolderListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repository: DeviceHolderListRepository

    private lateinit var viewModel: DeviceHolderListViewModel


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = DeviceHolderListViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getList should update resultFlow with Success when repository returns data`() =
        runTest {
            // Arrange
            val customerList = listOf(
                Customer("John", "Male", 1, "image_url", "Doe", "1234567890", emptyList())
            )
            val deviceHolder = DeviceHolder(customerList)
            val expectedResult = Resource.Success(deviceHolder)

            Mockito.`when`(repository.getList()).thenReturn(flowOf(expectedResult))

            // Act
            viewModel.getList()

            // Advance the time by a sufficient amount to allow the flow to propagate
            advanceTimeBy(1000)

            // Assert
            val result = viewModel.resultFlow.value
            assertEquals(expectedResult, result)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getList should update resultFlow with Failure when repository throws an exception`() =
        runTest {
            // Arrange
            val exception = NullPointerException()
            val expectedResult = Resource.Failure(exception)

            runBlocking {
                `when`(repository.getList()).thenReturn(flow { throw exception })
            }

            // Act
            viewModel.getList()

            // Advance the time by a sufficient amount to allow the flow to propagate
            advanceTimeBy(1000)

            // Assert
            val result = viewModel.resultFlow.value
            assertEquals(expectedResult.toString(), result.toString())
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain() // Reset the main dispatcher after the test completes
        testDispatcher.cancel()
    }
}