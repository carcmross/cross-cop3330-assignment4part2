package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

class ToDoListModelTest {

    @Test
    void addNewTask_adds_task_to_list() {
        ToDoListModel model = new ToDoListModel();
        // Create a list named "actual" composed of two tasks with initialized strings
        ObservableList<Task> actual = FXCollections.observableArrayList();
        Task task1 = new Task("No", "Task 1", "2021-07-08");
        Task task2 = new Task("No", "Task 2", "2021-07-09");
        Task task3 = new Task("No", "Task 3", "2021-07-10");
        actual.addAll(task1, task2);
        // addNewTask("task description here", "task due date here", actual);
        model.addNewTask(task3.getDesc(), task3.getDueDate(), actual);
        // Set expected to an ObservableList with the same parameters that were entered for the above assignments
        ObservableList<Task> expected = FXCollections.observableArrayList();
        expected.addAll(task1, task2, task3);
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void changeView_displays_all() {
        ToDoListModel model = new ToDoListModel();
        // Create a list named "actual" composed of three tasks with initialized strings
        // Set the completed variable in tasks two to false, and true for the others
        ObservableList<Task> tempList = FXCollections.observableArrayList();
        Task task1 = new Task("No", "Task 1", "2021-07-08");
        Task task2 = new Task("Yes", "Task 2", "2021-07-09");
        Task task3 = new Task("No", "Task 3", "2021-07-10");
        tempList.addAll(task1, task2, task3);

        // changeView("View All", actual, tempList)
        ObservableList<Task> actual = FXCollections.observableArrayList();
        actual = model.changeView("View All", tempList);

        // Set expected to a ObservableList composed of every task from actual
        ObservableList expected = FXCollections.observableArrayList();
        expected.addAll(task1, task2, task3);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void changeView_displays_completed_only() {
            ToDoListModel model = new ToDoListModel();
            // Create a list named "actual" composed of three tasks with initialized strings
            // Set the completed variable in tasks two to false, and true for the others
            ObservableList<Task> tempList = FXCollections.observableArrayList();
            Task task1 = new Task("No", "Task 1", "2021-07-08");
            Task task2 = new Task("Yes", "Task 2", "2021-07-09");
            Task task3 = new Task("No", "Task 3", "2021-07-10");
            tempList.addAll(task1, task2, task3);

            // changeView("View All", actual, tempList)
            ObservableList<Task> actual = FXCollections.observableArrayList();
            actual = model.changeView("View Completed", tempList);

            // Set expected to a ObservableList composed of every task from actual
            ObservableList expected = FXCollections.observableArrayList();
            expected.addAll(task2);
            assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void changeView_displays_incomplete_only() {
        ToDoListModel model = new ToDoListModel();
        // Create a list named "actual" composed of three tasks with initialized strings
        // Set the completed variable in tasks two to false, and true for the others
        ObservableList<Task> tempList = FXCollections.observableArrayList();
        Task task1 = new Task("No", "Task 1", "2021-07-08");
        Task task2 = new Task("Yes", "Task 2", "2021-07-09");
        Task task3 = new Task("No", "Task 3", "2021-07-10");
        tempList.addAll(task1, task2, task3);

        // changeView("View All", actual, tempList)
        ObservableList<Task> actual = FXCollections.observableArrayList();
        actual = model.changeView("View Incomplete", tempList);

        // Set expected to a ObservableList composed of every task from actual
        ObservableList expected = FXCollections.observableArrayList();
        expected.addAll(task1, task3);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void editTask_edits_the_contents_of_a_task() {
        // Create a list called "tempList" with one element in it named "listOne"
        // Add three tasks with initialized strings to listOne
        // actual = editTask("taskTwoOldName", "taskTwoNewName", "taskTwoNewDesc", "taskTwoNewDueDate", tempList, 0)
        // Set expected to a list with the same values of tempList, including the now changed task two values
        // assertEquals(expected, actual)
        ToDoListModel model = new ToDoListModel();
        // Create a list named "actual" composed of three tasks with initialized strings
        // Set the completed variable in tasks two to false, and true for the others
        ObservableList<Task> actual = FXCollections.observableArrayList();
        Task task1 = new Task("No", "Task 1", "2021-07-08");
        Task task2 = new Task("Yes", "Task 2", "2021-07-09");
        Task task3 = new Task("No", "Task 3", "2021-07-10");
        Task edited = new Task("Yes", "Cooler Task 2", "2021-07-11");
        actual.addAll(task1, task2, task3);

        // editTask("Task 2", "Cooler Task 2", "2021-07-11", actual)
        model.editTask("Task 2", "Cooler Task 2", "2021-07-11", actual);

        // Set expected to a ObservableList composed of every task from actual
        ObservableList expected = FXCollections.observableArrayList();
        expected.addAll(task1, edited, task3);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void deleteTask_removes_a_task_from_a_list() {

        ToDoListModel model = new ToDoListModel();
        // Create a list named "actual" composed of three tasks with initialized strings
        ObservableList<Task> actual = FXCollections.observableArrayList();
        Task task1 = new Task("No", "Task 1", "2021-07-08");
        Task task2 = new Task("No", "Task 2", "2021-07-09");
        Task task3 = new Task("No", "Task 3", "2021-07-10");
        actual.add(task1);
        actual.add(task2);
        actual.add(task3);
        // Set actual to deleteTask("taskDesc", actual, 0)
        model.deleteTask("Task 3", actual);
        // Set expected to an ObservableList with the same parameters that were entered for the above assignments
        ObservableList<Task> expected = FXCollections.observableArrayList();
        expected.add(task1);
        expected.add(task2);
        // assertEquals(expected, actual)
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void loadList_loads_single_list_into_taskViewer() {
        ToDoListModel model = new ToDoListModel();
        // Set ObservableList named actual to loadList("LoadSingleTest.txt")
        ObservableList<Task> actual = FXCollections.observableArrayList();
        actual = model.loadList(new File("src/test/resources/loadListTestInput.txt"));
        // Set expected to a string of the text as written in LoadSingleTest.txt
        ObservableList<Task> expected = FXCollections.observableArrayList();
        expected.add(new Task("Yes", "Assignment 4 Part 2", "2021-07-12"));
        // AssertEquals(expected, actual)
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void writeToFile_saves_single_list_to_txt_file() {
        ToDoListModel model = new ToDoListModel();
        String input = "Description: Task 1\nDue Date: 2021-07-08\nCompleted: No\n\nDescription: Task 2\n" +
                "Due Date: 2021-07-09\nCompleted: Yes\n\nDescription: Task 3\nDue Date: 2021-07-10\n" +
                "Completed: No\n\n";
        // Call saveList("SaveListTest.txt", input);
        model.writeToFile(new File("src/test/resources/SaveListTest.txt"), input);
        // Read the SaveTestSingle.txt file and assign the string to actual
        String actual = "";
        try {
            Scanner in = new Scanner(Paths.get("src/test/resources/SaveListTest.txt"));
            while (in.hasNext()) {
                actual += in.nextLine();
                actual += "\n";
            }
            actual += "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set expected to the expected string using String.format and tabs
        String expected = "Description: Task 1\nDue Date: 2021-07-08\nCompleted: No\n\nDescription: Task 2\n" +
                "Due Date: 2021-07-09\nCompleted: Yes\n\nDescription: Task 3\nDue Date: 2021-07-10\n" +
                "Completed: No\n\n";
        assertEquals(expected, actual);
    }

    @Test
    void generateSaveOutput_formats_output_correctly() {
        ToDoListModel model = new ToDoListModel();
        // Create a list named "actual" composed of three tasks with initialized strings
        // Set the completed variable in tasks two to false, and true for the others
        ObservableList<Task> tempList = FXCollections.observableArrayList();
        Task task1 = new Task("No", "Task 1", "2021-07-08");
        Task task2 = new Task("Yes", "Task 2", "2021-07-09");
        Task task3 = new Task("No", "Task 3", "2021-07-10");
        tempList.addAll(task1, task2, task3);

        //
        String actual = model.generateSaveOutput(tempList);
        String expected = "Description: Task 1\nDue Date: 2021-07-08\nCompleted: No\n\nDescription: Task 2\n" +
                            "Due Date: 2021-07-09\nCompleted: Yes\n\nDescription: Task 3\nDue Date: 2021-07-10\n" +
                            "Completed: No\n\n";
        assertEquals(expected, actual);
    }

    @Test
    void wrapIfLong_wraps_string_to_next_line_if_longer_than_155_characters() {
        ToDoListModel model = new ToDoListModel();

        // Create a string longer than 150 characters
        String longString = "CyRqVYYnLD tRNADhjtFH zKqzPJiNUe mMzvadtTZG THdgxwtxjQ lFZdBISqcb RRGxNsWVBP gWzGCK" +
                "SCJW FwdrfSSyHH luwysMaORt HAWaKjdaVE qdgXuLUjme ZaxQvwjWaE HeJVltWufL AmfaEDXWFh hwvQtLTGFn";


        String actual = model.wrapIfLong(longString);
        System.out.println(actual);
        String expected = "CyRqVYYnLD tRNADhjtFH zKqzPJiNUe mMzvadtTZG THdgxwtxjQ lFZdBISqcb RRGxNsWVBP gWzGCK" +
                "SCJW FwdrfSSyHH luwysMaORt HAWaKjdaVE qdgXuLUjme ZaxQvwjWaE HeJVl-\ntWufL AmfaEDXWFh hwvQtLTGFn";
        assertEquals(expected, actual);
    }
}