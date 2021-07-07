package ucf.assignments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

class ToDoListModelTest {

    @Test
    void addNewList_adds_list_to_arraylist() {
        // Declare an empty ObservableList named "tempList"
        // Set actual to addNewList("listOne", tempList);
        // Set expected to to an ObservableList with one element whose title is listOne
        // assertEquals(expected, actual)
    }

    @Test
    void removeList_removes_list_from_arraylist() {
        // Hard-code a list called "tempList" of 3 to-do lists named "listOne", "listTwo", and "listThree"
        // Set actual to removeList("ListTwo", tempList)
        // Set "expected" variable to the list without the removed list
        // assertEquals(expected, actual)
    }

    @Test
    void addNewTask_adds_task_to_list() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Create a list named "tempTasks" composed of two tasks with initialized strings
        // Set actual to addNewTask("task name here", "task description here", "task due date here", tempList, 0);
        // Set expected to an ObservableList with the same parameters that were entered for the above assignments
        // assertEquals(expected, actual)
    }

    @Test
    void changeView_displays_all() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Create a list inside of tempList called "tempTasks" composed of five tasks with initialized strings
        // Set the completed variable in tasks two and four to false, and true for the others
        // Create a TableView named tempView
        // Set actual to changeView("View All", tempList, tempView, 0)
        // Set expected to a TableView composed of every task from ListOne
        // assertEquals(expected, actual)
    }

    @Test
    void changeView_displays_completed_only() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Create a list inside of tempList called "tempTasks" composed of five tasks with initialized strings
        // Set the completed variable in tasks two and four to false, and true for the others
        // Set actual to changeView("View Completed", tempList, taskView, 0)
        // Set expected to a TableView composed of every task from ListOne whose completed value is true
        // assertEquals(expected, actual)
    }

    @Test
    void changeView_displays_incomplete_only() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Add five tasks with initialized strings to listOne
        // Set the completed variable in tasks two and four to false, and true for the others
        // Set actual to changeView("View Incomplete", tempList, taskView, 0)
        // Set expected to a TableView composed of every task from ListOne whose completed value is false
        // assertEquals(expected, actual)
    }

    @Test
    void editListTitle_changes_the_title_of_a_list() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Set actual to editListTitle("ListOne", "ListUno", tempList)
        // Set expected to a list with one element in it named "ListUno"
        // assertEquals(expected, actual)
    }

    @Test
    void editTask_edits_the_contents_of_a_task() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Add three tasks with initialized strings to listOne
        // actual = editTask("taskTwoOldName", "taskTwoNewName", "taskTwoNewDesc", "taskTwoNewDueDate", tempList, 0)
        // Set expected to a list with the same values of tempList, including the now changed task two values
        // assertEquals(expected, actual)
    }

    @Test
    void deleteTask_removes_a_task_from_a_list() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Create a list inside of tempList called "tempTasks" composed of three tasks with initialized strings
        // Set actual to deleteTask("taskTitle", tempList, 0)
        // Set expected to a list with the same values of tempList, excluding the now deleted task two
        // assertEquals(expected, actual)
    }

    @Test
    void viewCurrentList_adds_indexed_list_to_taskview() {
        // Create a list called "tempList" with three elements in it named "listOne", "listTwo", and "listThree"
        // Add three tasks with initialized strings to each list
        // Declare a TableView<Task> named actual
        // actual = viewCurrentList(tempList, actual, 1, "View All"
        // set expected to a TableView<Task> with the values of listTwo (index 1)
        // assertEquals(expected, actual)
    }

    @Test
    void loadMultipleLists_loads_lists_into_taskViewer() {
        // Set string named actual to loadMultipleLists("LoadMultipleTest.txt", "")
        // Set expected to a string of the text as written in LoadMultipleTest.txt
        // AssertEquals(expected, actual)
    }

    @Test
    void loadList_loads_single_list_into_taskViewer() {
        // Set string named actual to loadList("LoadSingleTest.txt", "")
        // Set expected to a string of the text as written in LoadSingleTest.txt
        // AssertEquals(expected, actual)
    }

    @Test
    void saveList_saves_single_list_to_txt_file() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Add three tasks with initialized strings to listOne
        // Call saveList("SaveTestSingle.txt");
        // Read the SaveTestSingle.txt file and assign the string to actual
        // Set expected to the expected string using String.format and tabs
        // assertEquals(expected, actual)
    }

    @Test
    void saveMultiple_saves_single_list_to_txt_file() {
        // Create a list called "tempList" with three elements in it named "listOne", "ListTwo", and "ListThree"
        // Add three tasks with initialized strings to each list
        // Call saveMultiple("SaveTestMultiple.txt");
        // Read the SaveTestMultiple.txt file and assign the string to actual
        // Set expected to the expected string using String.format and tabs
        // assertEquals(expected, actual)
    }
}