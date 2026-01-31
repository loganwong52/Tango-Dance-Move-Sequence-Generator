# Setting up JavaFX library:
1. File -> Project Structure

2. Under Project Settings:
click 
Libraries

3. Click the + button
4. Under New Project Library, choose Java

5. Select C:\

6. Double click the folder: 
javafx-sdk-21

7. Double click the folder:
lib

8. NOW, click the Select Folder button

9. Click Apply

# When you click the green play button:
DanceVisualizer will give you an error.

1. Click the 3 dots

2. Choose "Edit..." under Configuration

3. In the Build and run section, click "Modify options" to see the dropdown menu

4. Click Add VM Options so the checkmark appears next to it.

5. In the new input box that appears, enter this:

--module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml
