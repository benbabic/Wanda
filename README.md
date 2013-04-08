Wanda
=====

Wanda Video File Server project.


Setup for Eclips
=====
1- Requirements

Be sure to have a git extension installed for eclipse like this one
	http://www.eclipse.org/egit/
Get git package for (Linux|Windows) 
	http://git-scm.com/downloads

2- import the project in your local repository

Using git in command line import the project (in your git folder):
 $ git init
 $ git add remote origin https://github.com/Ornro/Wanda.git
Or use ssh connection but be sure to give your key to github (this avoids typing in password on every push)
 $ git pull origin master
 
3- Import project in eclipse

 Eclipse -> File -> Import -> 
 Git -> Project from Git -> Local -> add (select your local copy of the git)

4- Configure classpath

 Project -> Properties -> Java Build Path -> Add Variable ->
 Configure Variables -> New
 Choose "WANDA_ROOT" as name and put the path of the wanda project you imported and which contains the includes folder
 e.g: C:/Documents and Settings/.../Workstation/Wanda
 
 Note: If the "WANDA_ROOT" variable is set just edit it to match with your wanda location.
 
 If you have any trouble contact developpers.

Developpers
=====
Benjamin Babic <benjamin.babic@hotmail.fr>
Valentin Boutonn�
Axel Robert
