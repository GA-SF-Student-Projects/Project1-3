Project #1: A To-Do List

The program is a list that holds to-do list. 
Starting the app, the user will see The List of Lists that is initially empty. 
There is a colored text box where the user will type in the title of his first list.
There is an add floating action button so the player can add the list title to the list. 
The user can also press enter. 
If the user long clicks on an item, parts of the layout and the clicked item will turn blue, showing that the user is in 'edit mode'.
The remove floating action button becomes visible in edit mode. 
The user can edit the currently highlighted text by pressing enter or add button.
Or the user can delete the list by pressing the Remove button. 
After pressing the add button/enter or remove button, it will exit 'edit mode' and the colors will go back. 
If the user clicks an item, it will send the user to the next page with the title of the item.
In the second list, the player can still do 'edit mode' as well strikethrough when the item is clicked on. 
Strikethroughs are independent from each other since each item in the second list is an object that stores it's string and a isStrikeThrough boolean.
All of the items from the first list are objects that store the title and their own individual arrayList. 
The lists save going back and forth between the lists. 

Known bugs:
- More than 1 item can be highlighted 
- StrikeThrough doesn't save going back and forth between lists
