# 2019-robot-code-new
FRC 2019 Project Code on VSCode System

## Install FRC VSCode 2019
https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/999999-installing-c-and-java-development-tools-for-frc

Download FRC VSCode installer:
https://github.com/wpilibsuite/allwpilib/releases

Download Java Development Kit:
https://www.oracle.com/technetwork/java/javase/downloads/index.html

### Windows
1) Install the correct version of the latest version of JDK for your PC.
2) Open the zip file, install and open FRC VSCode 2019.

### Mac
1) Install the correct version of the latest version of JDK for your Mac.
2) Open the tar.gz file, move the WPILib_Mac folder to the home directory and rename it "frc2019". 
3) Open Terminal (/Applications/Utilities/Terminal.app), type "cd frc2019/tools" and hit enter.
4) Type "python ToolsUpdater.py" and hit enter.
5) Download and install Visual Studio Code from [code.visualstudio.com], then open it.
6) In Visual Studio, open the command palette (Cmd + Shift + P) and click on/search for "Extensions: Install from VSIX...".
7) Navigate to the "frc2019" folder, and go to the "vsCodeExtensions" folder inside.
8) Select "Cpp.vsix" and open it.
9) Click "Reload" in the dialog box that pops up in the bottom corner of the VSCode window.
10) Repeat steps 6-8 for each of the VSIX files in the "vsCodeExtensions" folder in the following order:
- Cpp.vsix
- JavaLang.vsix
- JavaDeps.vsix
- JavaDebug.vsix
- WPILib.vsix

## Install CTRE Phoenix Libraries
Download the latest version of the CTRE Phoenix Framework installer here:
https://github.com/CrossTheRoadElec/Phoenix-Releases/releases/

Download the correct zip file for your system and unzip.
Optional: Download only the Phoenix Tuner installer and not the rest of the CTRE Phoenix libraries.

### Windows
1) Run the installer.
2) Click "Finish" when the install is complete
### Mac
1) Place the "maven" and "vendordeps" folders inside the frc2019 folder in the home directory..
2) Open VSCode.
3) Open the command palette (Cmd + Shift + P) & click on/search for "WPILib: Manage Vendor Libraries".
4) Select "Install new library (online)".
5) Check the box next to "CTRE Phoenix" and click "OK".

#### The following program install instructions are Windows-only. Unfortunately, there is no Mac version of LabView 2018 or the FRC Suite as of now.
## Installing LabView 2018 and NI Vision Software (Optional, Recommended):
1) Download the LabView 2018 installer (~5-6 GB) here: http://www.ni.com/download/labview-for-frc-18.0/7841/en/. You will have to create or use an existing National Instruments account.
2) Extract all the files inside the zip file into a new folder. This may take a while.
3) Run autorun.exe, found inside the folder with the extracted files.
4) Close all other programs.
5) Select "Install everything for LabView Development" or "Install only NI Vision Software" depending on your needs.
6) Click "Next" through all the windows (Allow NI to automatically check for updates).
7) When you reach the "User Information" window, enter your full name, and "FRC Team 6238" for your organization. (If you're not on the Popcorn Penguins, enter your team number. Also, thank you for looking at our repo :). )
8) Under serial number, enter the serial number you get from the team coach. (If you're on the Popcorn Penguins, you can ask Mr. Hondl or Ishan too)
9) Click "Next", "I accept the above 3 License Agreement(s)", "Next", "I accept the above 2 License Agreement(s)", and "Next".
10) Make sure the box next to "Always trust software from National Instruments Corporation." is checked, then click "Next".
11) Make sure the box next to "Disable Windows fast startup..." is checked, then click "Next".
12) Click "Next" on the window titled "Start Installation".
13) Allow LabView 2018 and/or NI Vision Software to install.
14) On the "Installation Summary" window, make sure the box next to "Run License Manager..." is checked, then click "Next". (You will need a working internet connection).
15) Log in to your NI account on the window that pops up.
16) In the Licensing Wizard window, enter the same serial number from before in all the boxes if it is not already there.
17) If the activation was successful, all the text boxes should be replaced by green "Activation Successful" text.
18) If all the software activated successfully, click "Next", otherwise double-check the serial number(s) entered.
19) Close the Activation Wizard.
20) Click "Yes" on the Restart dialog, then wait for your computer to restart.
21) **Do not install any updates you are prompted to by the NI Update Service unless directed to do so by FRC.**

## Installing the FRC 2019 Update Suite:
https://wpilib.screenstepslive.com/s/currentCS/m/labview/l/1027502-installing-the-frc-update-suite-all-languages
1) Download the FRC 2019 installer (~700-800 MB) here: http://www.ni.com/download/first-robotics-software-2017/7904/en/. You will have to create or use an existing National Instruments account. Enter the decryption key *$Robots&in#SPACE!!* if prompted to do so.
2) Extract all the files inside the zip file into a new folder.
3) Run setup.exe, found inside the folder with the extracted files.
4) Close all other programs.
5) Click "Next" in the installer window.
6) Click "Next" again. There is no need to deselect the LabView update; it will not install if LabView is not already installed on your computer.
7) Make sure the box is checked, then click "Next".
8) When you reach the "User Information" window, enter your full name, and "FRC Team 6238" for your organization. (If you're not on the Popcorn Penguins, enter your team number. Also, thank you for looking at our repo :). )
9) Select "I accept the above 2 License Agreement(s)", then click "Next".
10) Repeat step 9 on the second license agreements window that appears.
11) Allow the Update Suite to install.
12) In the window that appears, check the box next to "Run License Manager...", then click "Next".
13) Log in to your NI account on the window that pops up.
14) In the Licensing Wizard window, enter the same serial number from before in all the boxes if it is not already there.
15) If the activation was successful, all the text boxes should be replaced by green "Activation Successful" text.
16) If all the software activated successfully, click "Next", otherwise double-check the serial number(s) entered.
17) Close the Activation Wizard.
18) Click "Yes" on the Restart dialog, then wait for your computer to restart.
19) **Do not install any updates you are prompted to by the NI Update Service unless directed to do so by FRC.**
