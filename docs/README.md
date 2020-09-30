# User Guide
The simple guide to using Duke, by Aldo Maximillian

## Features 

### Feature 1 
The user is able to add tasks

### Feature 2 
The user is able to delete tasks

### Feature 3 
The user is able to search for tasks

### Feature 4 
The user is able to mark individual tasks as done


## Usage

### `todo` - Adds a todo

Todo is a command to add a task. Todo is a plain task with only a task name.

Example of usage: 

`todo clean up room`

Expected outcome:

```
=================================================
 	Got it. I've added this task
 		[T][✗] clean up room
 	Now you have 1 tasks in the list.
=================================================
```
### `event <event_name> /at <event_time>` - Adds an event

Event is a command to add an event to the task lst. Event is a task with a set time.

Example of usage: 

`event sleep in my bedroom /at 4am tomorrow`

Expected outcome:

```
=================================================
	Got it. I've added this task
		[E][✗] sleep in my bedroom (at: 4am tomorrow)
	Now you have 2 tasks in the list.
=================================================
```
### `deadline <deadline_name> /by <deadline_time>` - Adds a Deadline

Deadline is a command to add a deadline. Deadline is a task a set deadline.

Example of usage: 

`deadline do CS2113T iP coding /by Friday 2 Oct`

Expected outcome:

```
=================================================
	Got it. I've added this task
		[D][✗] do cs2113t ip coding (by: friday 2 oct)
	Now you have 3 tasks in the list.
=================================================
```
### `list` - Lists all tasks

This command will list all the task in the task list. 

Example of usage: 

`list`

Expected outcome:

```
=================================================
    Here are the tasks in your list:
        1. [T][✗] clean up laundry
        2. [E][✗] sleep in my bedroom (at: 4am tomorrow)
        3. [D][✗] do cs2113t ip coding (by: friday 2 oct)

=================================================
```
### `done <task_index>` - Mark a task as done

This command will set the task according to the task index to done.

Example of usage: 

`done 2`

Expected outcome:

```
=================================================
    Nice! I've marked this task as done: 
        [E][✓] sleep in my bedroom (at: 4am tomorrow)
=================================================
```
### `delete <task_index>` - Deletes selected Task

This command will delete the task according to the task index.

Example of usage: 

`delete 2`

Expected outcome:

```
=================================================
    Got it. I've deleted this task
        [E][✓] sleep in my bedroom (at: 4am tomorrow)
    Now you have 2 tasks in the list
=================================================
```
### `search <search_keyword>` - Searches the task list

This command will delete the task according to the task index.

Example of usage: 

`search clean`

Expected outcome:

```
=================================================
    Here is what I found for "clean"
        [T][✗] clean up laundry
=================================================
```
### `help` - Shows the help page

This command will show the help message for the user

Example of usage: 

`help`

Expected outcome:

```
=================================================
How to use DUKE
A. ADD A TASK
    1. Todo - Task with only the description
        todo <task description>
    2. Deadline - Task with a set deadline 
        deadline <task description> /by <date of completion>
    3. Event - Task with a set date
        event <task description> /at <date of event>
B. LIST THE TASK
     list
C. SEARCH THE TASK LIST
     find <search_keyword>
D. MARK THE TASK AS DONE
     done <task number>
E. DELETE THE TASK
     delete <task number>
F. END THE PROGRAM
     bye
=================================================
```
### `bye` - Terminates and exits the program

This command will end the program

Example of usage: 

`bye`

Expected outcome:

```
=================================================
	Bye. Hope to see you again soon!
=================================================
```


