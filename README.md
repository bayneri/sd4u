#Content Development Tool

Authors:
Halil Cetiner
Yusuf Hakan Kalayci

Supervisor : Haluk Bingol

Purpose of the Content Development Tool project is to make slides and presantations which stores information in Html5 format using with simple user interface. It is developed with JavaFx and desktop application.

Basically the Content Development Tool is used to make presantations with multiple slides. Mainly it is developed for programming lectures. We also added code displayers and options to switch code.

Following a period of rapid development since March of 2015, we discussed with our professor for this project and our researches suggest that this project can help people who is using slides with their programming lectures.

#Acknowledgements

We would like to thank the founder and supervisor of The Content Development Tool project Haluk Bingol. This project was develeoped for CMPE160 lecture in Bogazici University. Project was developed by Yusuf Hakan Kalayci and Halil Cetiner.

#Introduction

The Contents Development Tool was a Boun-Cmpe project that aimed to create slides which use some properties of Html5 format such as convenience of modification and flexibility between different platforms. We can create presentations with this tool that can be used in websites, eclipse plugins.

The tool is developed with JavaFx and it uses some additional data such as html files and css files. This addtional data provide creating default pages or adding additional facilities and setting up default view.

There are some projects which are similar to ours but they are commonly developed for html and as browser application. This project was developed as desktop application. And also this project can be modified and developed more.

#Desing and Implementation

1) Setting up environment

As we mentioned this project is JavaFx project then you must setup eclipse for javafx. You can adjust your eclipse by using this link(http://www.eclipse.org/efxclipse/install.html) or you can also download all-in-one ide which is e(fx)clipse from that link.

After installing ide, you can import our project to your workspace.

2) User Interface
	
a) Menu
In this menu we created some menu items which provides quick access some operations. These operations can be listed as file operations, editor operations.

b) List
In this section we are listing slides which are in same project. Users can reach slides by using List.
It has 2 operations which edit slide list: adding slide and removing slide.

c) Extended Html Editor
In this section users can edit current slide. This section has many buttons and a Html5 viewer for displaying current view. 

i) Html5 Viewer
Users can edit current view of slide by using Html5 viewer. They can see last state and they can write on it or they can add some special fragments by using buttons.

ii) Buttons
We have some buttons which are gained from HtmlEditor in JavaFx. These buttons are used for basic operations such as changing font size and type, aligning text, indentation, listing and etc.

We have also some buttons which are created by us. They can be listed such as:
	DC (Double Column): This button adds double column at cursor position. 
	T (Theorem): This button adds theorem fragment which can be used for specify therorems.
	R (Remark): This button adds a fragment which can be used for remarking some points .
	D (Definition): This button adds definition fragment which can be used for specify definitions.
	Show/Hide: This button adds a area which can be hidden and shown.
	Image: This button displays a pop-up window and using this window users can add images to slides. These images can be added from both 	internet and local files.
	Code: This button displays a pop-up windows and using this window users can write codes to slides. While displaying slide this code fragment beautify codes.
	Code File: This button displays a pop-up window and using this window users can add switch to code button to their slides. This switch to code button provides quick access to ide.
	Latex: This button displays a pop-up window and using this window users can add mathmatical terms and expressions easily in latex format.
	Layout Chooser: With this JavaFx combobox users can change layout of slide.

On the other hand, we are using similar processes to apply. In these processes we have some html code fragments for each button we are adding this fragments to html text of current slide. While adding fragment we are executing javascript codes in Html5 Viewer. These parts are handled in HTMLCodeFragmentInserter class.

3) Background

 a) Basic Html Operations
In this part we implemented a HTMLReader class which reads html codes.
On the other hand we are using method which works with parse-edit-merge logic. Let us explain parse-edit-merge logic:
	When we looked slides in html files we faced with a default layout. This layout can be explained in 5 parts. These parts can be seen at figure.
	--figure1--

This tool does not edit fixed parts because our slides has identical theme. However by using this tool we can edit containers which are named title and content.

We are handling this operation with HTMLParser and HTMLMerger classes. HTMLParser class detects editable parts of html file and parses them. However, HTMLMerger class merges editable parts and fixed parts in predefined order which is shown in figure.

 b) History
In this part we will explain history operations such as undo and redo.
We are keeping some specified actions of users on the html file then providing them to use undo and redo.

 c) Garbage Cleaner
JavaFx HtmlEditor adds some unnecessary codes to html file of slide, and our javascript files are adding some code to html file of slide at loading. While we are editting these garbages may cause some problems such as multiple "Theorem" or "Remark" expressions so we must clean up them. By using garbage cleaner we are cleaning up these garbages.

 d) Image Download
Some slides can have images. We decided to copy images which are added to slide in fixed directory such /images or directory with name of slide. We are getting images to this directory by using this part.

 e) Save
Our project provides users two different save options:

 i) Save singular slide: This option saves just current slide to "bin/WebContent/SubjectA/SavedFiles" named as current date and time.
 ii) Save whole project: This option saves whole project to "bin/WebContent/SubjectA/SavedFiles" named as current date and time.

4) Conclusion
In conclusion we have done fundemental part of the Content Development Tool. We developed Extended Html Editor which has special abilities to creating programming lectures' slides. We also prepared javaWe developed parts of project to provide convenience to maintenance and furthermore development. Especially we implemented html package and CodeFragmentInserter class considering this concept of convenience.

In our opinions, this project can be developed furthermore in many aspects. Firstly there can be a html code displayer to edit manually slide (without user interface), there can be minimized view on list of slides.

On the background, there may be problems with handling some unexpected behaviors, these kinds of behaviors can be handled more effectively.



