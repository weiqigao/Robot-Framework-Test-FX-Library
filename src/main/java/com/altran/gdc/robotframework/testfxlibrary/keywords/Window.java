/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.altran.gdc.robotframework.testfxlibrary.keywords;

import com.altran.gdc.robotframework.testfxlibrary.exceptions.TestFxLibraryFatalException;
import com.altran.gdc.robotframework.testfxlibrary.exceptions.TestFxLibraryNonFatalException;
import com.altran.gdc.robotframework.testfxlibrary.utils.TestFxLibraryCommon;
import com.altran.gdc.robotframework.testfxlibrary.utils.TestFxLibraryValidation;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkitContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author pcosta
 */
@RobotKeywords
public class Window {

    private String filePath;
    private int counter;
    private String fileName;
    private String format;
    private static final Logger LOG = LoggerFactory.getLogger(Window.class);
    private static final String ERROR_MSG = "Error";
    private static final String GENERAL_ERROR_MSG = "Something went wrong";
    private static final int COMP_POSITION_SIZE = 2;

    @Autowired
    private Wait wait;

    private static void run() {
        try {
            new FxToolkitContext().getPrimaryStageFuture().get().setMaximized(true);
        } catch (InterruptedException | ExecutionException e) {
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryNonFatalException(GENERAL_ERROR_MSG, e);
        }
    }

    /**
     * <b>Description:</b> This keyword closes the current focused window. If an error occurs
     * a TestFxLibraryNonFatalException is thrown.<br>
     */
    @RobotKeyword
    public void closeCurrentWindow() {
        try {
            new FxRobot().closeCurrentWindow();
        } catch (Exception e){
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryNonFatalException("Error Close Window");
        }
    }

    /**
     * <b>Description:</b> This keyword returns a list of all target windows.<br>
     *
     * @return
     *  A list of javafx.stage.Window
     */
    @RobotKeyword
    public List<javafx.stage.Window> listTargetWindows() {

        return new FxRobot().listTargetWindows();
    }

    /**
     * <b>Description:</b> This keyword returns a list of all windows.<br>
     *
     * @return
     *  A list of javafx.stage.Window
     */
    @RobotKeyword
    public List<javafx.stage.Window> listWindows() {

        return new FxRobot().listWindows();
    }

    /**
     * <b>Description:</b> This keyword targets a specific window specified with <i>identifier</i>.<br>
     *
     * @param identifier
     * : The identifier of the window
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>identifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     *
     * <br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>Select Window</td>
     *         <td>idWindow02</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    @ArgumentNames({"identifier"})
    public void selectWindow(String identifier) {

        javafx.stage.Window window = new FxRobot().targetWindow(identifier).targetWindow();
        Platform.runLater(window::requestFocus);

    }

    /**
     * <b>Description:</b> This keyword chooses a specific window specified with <i>identifier</i>.<br>
     *
     * @param identifier
     * : The identifier of the window
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>identifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     *
     * <br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>Window</td>
     *         <td>idWindow23</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    @ArgumentNames({"identifier"})
    public void window(String identifier) {

        new FxRobot().window(identifier);
    }

    /**
     * <b>Description:</b> This keyword selects the main window. If an error occurs
     * a TestFxLibraryNonFatalException is thrown.<br>
     *
     */
    @RobotKeyword("Select the mais Window\n\n" +
            "Example:\n" +
            "Select Main Window")
    public void selectMainWindow(){
        try {
            new FxToolkitContext().getPrimaryStageFuture().get();
        } catch (Exception e){
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryNonFatalException("Error get main window");
        }
    }

    /**
     * <b>Description:</b> This keyword takes a screenshot of the application. File
     * extension is specified with <i>format</i>. This file is saved with the filename
     * and filepath specified with the keyword Set File Path.
     * If an error occurs a TestFxLibraryNonFatalException
     * is thrown.<br>
     *
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>format</td>
     *         <td>Yes</td>
     *         <td>string (jpg, png, gif, bmp, wbmp)</td>
     *         <td>png</td>
     *     </tr>
     * </table>
     *
     * <br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>Capture Screen</td>
     *         <td>jgp</td>
     *         <td></td>
     *     </tr>
     *     <tr>
     *         <td>Set File Path</td>
     *         <td>E:\MyFolder\MyInnerFolder\</td>
     *         <td>myFile</td>
     *     </tr>
     *     <tr>
     *         <td>Saved file:</td>
     *         <td>E:\MyFolder\MyInnerFolder\myFile.jpg</td>
     *         <td></td>
     *     </tr>
     * </table>
     * <br>
     */
    @RobotKeyword
    public void captureScreen(){

        TestFxLibraryValidation.validateArguments(format);

        try {

            Stage primaryStage = new FxToolkitContext().getPrimaryStageFuture().get();
            Rectangle r = new Rectangle();
            r.setBounds((int)primaryStage.getX(),(int)primaryStage.getY(),(int)primaryStage.getWidth(),(int)primaryStage.getHeight());

            BufferedImage image = new Robot().createScreenCapture(r);
            String fp = filePath + fileName + counter + "." + format;
            ImageIO.write(image, format, new File(fp));
            //This System out print can't be removed since it is respossible for the enbbeding of the ScreenShot on the Log file
            System.out.println("*HTML* <img src=\""+ "file://" + fp.replace("\\","/") + "\">");
            counter++;

        } catch (Exception e){
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryNonFatalException("Error taking screenshot");
        }
    }

    /**
     * <b>Description:</b> This keyword sets the folder path where you want to save screenshots,
     * sets the filename, deletes files with the same name in this folder and resets the counter
     * of screenshots. <i>filePath</i> specifies the path and <i>fileName</i> specifies the filename.
     * If an error occurs a TestFxLibraryNonFatalException is thrown.<br>
     *
     * @param filePath
     * : Path of the folder where you want to save screenShots
     * @param fileName
     * : Name that you want to give to Screenshots
     * @param format
     * : Image format
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>filePath</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     *     <tr>
     *         <td>fileName</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     *
     * <br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>Set File Path</td>
     *         <td>E:\MyFolder\MyInnerFolder\</td>
     *         <td>myFile</td>
     *     </tr>
     * </table>
     *
     */
    @RobotKeyword
    @ArgumentNames({"filePath" , "fileName", "format"})
    public void setFilePath(String filePath, String fileName, String format){

        TestFxLibraryValidation.validateArguments(filePath, fileName);

        try {
            if (!filePath.endsWith("/")) {
                this.filePath = filePath + "/";
            }else {
                this.filePath = filePath;
            }
            this.fileName = fileName;
            this.format = format;
            deleteFiles();
            counter = 1;

        } catch (Exception e){
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryFatalException("Error setting the filePath");
        }
    }

    private void deleteFiles(){

        File directory = new File(filePath);

        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.getName().startsWith(fileName + format)) {
                    f.delete();
                }
            }
        }
    }

    /**
     * <b>Description:</b> This keyword returns the position of a component on
     * the screen. This component is specified with <i>identifier</i>.<br>
     *
     * @param identifier
     * : Component identifier
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>identifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     *
     * @return
     *  Position of component on screen
     *
     * <br><br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>${position2D}=</td>
     *         <td>Get Component Position</td>
     *         <td>idComponent02</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    @ArgumentNames({"identifier"})
    public int[] getComponentPosition(String identifier) {

        TestFxLibraryValidation.validateArguments(identifier);
        wait.waitUntilPageContains(identifier);

        try {

            Point2D p = new FxRobot().point(identifier).query();
            int[] position = new int[COMP_POSITION_SIZE];
            position[0] = (int)p.getX();
            position[1] = (int)p.getY();
            return position;

        } catch (Exception e) {
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryFatalException(GENERAL_ERROR_MSG);
        }
    }

    /**
     * <b>Description:</b> This keyword returns the component size with <i>Width</i> and <i>Height</i>.
     * This component is specified with <i>identifier</i>.<br>
     *
     * @param identifier
     * : Component identifier
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>identifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     *
     * @return
     *  Position of component on screen
     *
     * <br><br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>#textField</td>
     *         <td>Get Component Size</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    @ArgumentNames({"identifier"})
    public int[] getComponentSize(String identifier) {

        TestFxLibraryValidation.validateArguments(identifier);
        wait.waitUntilPageContains(identifier);

        try {
            Node node = new FxRobot().lookup(identifier).query();

            return new int[]{(int) node.getBoundsInLocal().getWidth(), (int) node.getBoundsInLocal().getHeight()};
        } catch (Exception e) {
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryFatalException(GENERAL_ERROR_MSG);
        }
    }

    /**
     * <b>Description:</b>This keyword returns the title of the current window. If an error occurs
     * a TestFxLibraryNonFatalException is thrown.<br>
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     * </table>
     *
     *  @return
     *  Title of the component
     *
     * <br><br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>#scrollPane</td>
     *         <td>#textField</td>
     *         <td>Scroll Component To View</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    public String getSelectedWindowTitle(){
        try {
            Stage stage;
            String windowTitle = "";
            List<javafx.stage.Window> windows = new FxRobot().listTargetWindows();
            for (javafx.stage.Window window : windows) {
                stage = (Stage) window;
                if (stage.isFocused()) {
                    windowTitle = stage.getTitle();
                }
            }
            return windowTitle;
        } catch (Exception e){
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryNonFatalException("Error retrieving current window");
        }
    }

    /**
     * <b>Description:</b>This keyword maximizes the application window. If an error occurs
     * a TestFxLibraryNonFatalException is thrown.<br>
     *
     */
    @RobotKeyword
    public void maximizeWindow(){

        try {
            Platform.runLater(Window::run);

        } catch (Exception e){
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryNonFatalException(GENERAL_ERROR_MSG);
        }
    }

    /**
     * <b>Description:</b> This keyword scrolls the component to view in the ScrollPane witch is specified with the <i>scrollPaneIdentifier</i>.
     * This component is specified with <i>identifier</i>.<br>
     *
     * @param scrollPaneIdentifier
     * : ScrollPane where component is contained
     * @param identifier
     * : Component identifier
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>identifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     *     <tr>
     *         <td>scrollPaneIdentifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     * <br><br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>#scrollPane</td>
     *         <td>#textField</td>
     *         <td>Scroll Component To View</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    @ArgumentNames({"scrollPaneIdentifier","identifier"})
    public void scrollComponentToView(String scrollPaneIdentifier , String identifier) {

        TestFxLibraryValidation.validateArguments(scrollPaneIdentifier,identifier);
        wait.waitUntilPageContains(scrollPaneIdentifier);
        wait.waitUntilPageContains(identifier);

        try {

            Node node = TestFxLibraryCommon.lookup(identifier);


            double y = node.getBoundsInParent().getMaxY();

            ScrollPane pane = TestFxLibraryCommon.lookup(scrollPaneIdentifier);

            double height = pane.getContent().getBoundsInLocal().getHeight();

            pane.setVvalue(y/height);

            Platform.runLater(node::requestFocus);

        } catch (Exception e) {
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryFatalException(GENERAL_ERROR_MSG);
        }
    }

    /**
     * <b>Description:</b> This keyword requests the focus to the component.
     * This component is specified with <i>identifier</i>.<br>
     *
     * @param identifier
     * : Component identifier
     * <br><br>
     * <table summary="">
     *     <tr>
     *         <th>Parameter</th>
     *         <th>Mandatory</th>
     *         <th>Values</th>
     *         <th>Default</th>
     *     </tr>
     *     <tr>
     *         <td>identifier</td>
     *         <td>Yes</td>
     *         <td>string</td>
     *         <td>N/A</td>
     *     </tr>
     * </table>
     * <br><br>
     * <b>Examples:</b>
     * <table summary="">
     *     <tr>
     *         <td>#textField</td>
     *         <td>Focus To Component</td>
     *     </tr>
     * </table>
     */
    @RobotKeyword
    @ArgumentNames({"identifier"})
    public void focusToComponent(String identifier) {

        TestFxLibraryValidation.validateArguments(identifier);
        wait.waitUntilPageContains(identifier);

        try {
            Node node = TestFxLibraryCommon.lookup(identifier);

            Platform.runLater(() -> {
                node.setFocusTraversable(true);
                node.requestFocus();
            });

        } catch (Exception e) {
            LOG.error(ERROR_MSG, e);
            throw new TestFxLibraryFatalException(GENERAL_ERROR_MSG);
        }
    }
}
