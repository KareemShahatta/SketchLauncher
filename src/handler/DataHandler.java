package handler;

import javafx.stage.FileChooser;
import launcher.StartUp;
import templates.launcherscreen.TextInfoType;

import java.io.*;
import java.util.Base64;

public class DataHandler
{
    private StartUp startUp;
    private String dataBasePath;
    private File dataFile = new File(System.getProperty("user.home") + "\\SketchLauncher's DataBase");

    public DataHandler(StartUp startUp)
    {
        this.startUp = startUp;
    }

    public boolean isDataBaseFolderExist() {
        if (dataFile.exists())
        {
            dataBasePath = dataFile.getPath();
        }

        return dataFile.exists();
    }
    public boolean isDataBaseFolderEmpty()
    {
        return (dataFile.list().length <= 0);
    }


    public void createDataBaseFolder() {
        dataFile.mkdir();
        dataBasePath = System.getProperty("user.home") + "\\SketchLauncher's DataBase";
    }
    public void createUserFile(String username , String email , String password , Boolean updates) throws IOException {
        File file = new File(dataBasePath + "/" + username + ".txt");
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        String encodedUserName = Base64.getEncoder().encodeToString(("SketchLauncher=Username=" + username).getBytes());
        String encodedEmail = Base64.getEncoder().encodeToString(("SketchLauncher=Email=" + email).getBytes());
        String encodedPassWord = Base64.getEncoder().encodeToString(("SketchLauncher=Password=" + password).getBytes());
        String encodedUpdates = Base64.getEncoder().encodeToString(("SketchLauncher=Updates=" + updates).getBytes());

        bufferedWriter.write(encodedUserName);
        bufferedWriter.newLine();
        bufferedWriter.write(encodedEmail);
        bufferedWriter.newLine();
        bufferedWriter.write(encodedPassWord);
        bufferedWriter.newLine();
        bufferedWriter.write(encodedUpdates);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
    void importUserFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(startUp.getCurrentLauncherScene().getWindow());
        if(selectedFile != null)
        {
            if (selectedFile.getName().contains(".txt")) {
                selectedFile.renameTo(new File(System.getProperty("user.home") + "\\SketchLauncher's DataBase\\" + selectedFile.getName()));

                String selectedFileName = selectedFile.getName();
                int pos = selectedFileName.lastIndexOf(".");
                if (pos > 0) {
                    selectedFileName = selectedFileName.substring(0, pos);
                }

                startUp.getSoundHandler().playSuccessSound();
                startUp.getScreenUtilInstance().editInformationMessage(startUp.getScreenHandler().getMenuPaneObject().getSuccessText(), selectedFileName + "'s data file have been successfully imported", TextInfoType.INFO);
                startUp.getScreenHandler().getMenuPaneObject().getSuccessText().setVisible(true);
            } else {
                startUp.getSoundHandler().playerErrorSound();
                startUp.getScreenUtilInstance().editInformationMessage(startUp.getScreenHandler().getMenuPaneObject().getErrorText(), "that is not a supported data file, Please make sure it's a (.txt) file", TextInfoType.ERROR);
                startUp.getScreenHandler().getMenuPaneObject().getErrorText().setVisible(true);
            }
        }
    }

    public boolean isUserFileExists(String username) {
        File file = new File(dataBasePath + "/" + username + ".txt");
        return file.exists();
    }
    public boolean isUserDataCorrupted(String username) throws FileNotFoundException {
        File file = new File(dataBasePath + "/" + username + ".txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        boolean field1 = true;
        boolean field2  = true;
        boolean field3  = true;
        boolean field4  = true;

        for(Object line : bufferedReader.lines().toArray())
        {
            String lineString = (String) line;
            String DecodedStringLine = new String(Base64.getDecoder().decode((lineString.getBytes())));
            String[] lineData = DecodedStringLine.split("=");

            if(lineData[0].equalsIgnoreCase("SketchLauncher"))
            {
                switch (lineData[1].toLowerCase())
                {
                    case "username":
                        field1 = false;
                    case "email":
                        field2 = false;
                    case "password":
                        field3 = false;
                    case "updates":
                        field4 = false;
                }
            }
        }

        return (field1 && field2 && field3 && field4);
    }


    public String getUserEmail(String username) throws FileNotFoundException {
        File file = new File(dataBasePath + "/" + username + ".txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        for(Object line : bufferedReader.lines().toArray())
        {
            String lineString = (String) line;
            String DecodedStringLine = new String(Base64.getDecoder().decode((lineString.getBytes())));
            String[] lineData = DecodedStringLine.split("=");

            if(lineData[0].equalsIgnoreCase("SketchLauncher"))
            {
                if (lineData[1].equalsIgnoreCase("email")) {
                    return lineData[2];
                }
            }
        }

        return null;
    }
    public String getUserPassword(String username)  throws FileNotFoundException {
        File file = new File(dataBasePath + "/" + username + ".txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        for(Object line : bufferedReader.lines().toArray())
        {
            String lineString = (String) line;
            String DecodedStringLine = new String(Base64.getDecoder().decode((lineString.getBytes())));
            String[] lineData = DecodedStringLine.split("=");

            if(lineData[0].equalsIgnoreCase("SketchLauncher"))
            {
                if (lineData[1].equalsIgnoreCase("password")) {
                    return lineData[2];
                }
            }
        }

        return null;
    }
    public String getUserUpdates(String username)  throws FileNotFoundException {
        File file = new File(dataBasePath + "/" + username + ".txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        for(Object line : bufferedReader.lines().toArray())
        {
            String lineString = (String) line;
            String DecodedStringLine = new String(Base64.getDecoder().decode((lineString.getBytes())));
            String[] lineData = DecodedStringLine.split("=");

            if(lineData[0].equalsIgnoreCase("SketchLauncher"))
            {
                if (lineData[1].equalsIgnoreCase("updates")) {
                    return lineData[2];
                }
            }
        }

        return null;
    }
}
