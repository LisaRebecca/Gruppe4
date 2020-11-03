git runterladen und installieren
You can download Git here: [https://git-scm.com/downloads](https://git-scm.com/downloads)

ausprobieren ob es funktioniert:
Windows-Taste + R 
cmd eingeben
-> jetzt ist man im Terminal
eingeben: git --version
Wenn es die Version ausgibt ist alles okay

euren Rechner mit Github.com Verbinden:
im Terminal eingeben:
git config --global user.name "Your Name"
git config --global user.email "your@email.com"

Jetzt wollen wir unser Projekt aus github.com auf unserem Rechner haben:
im Terminal eingeben:
git clone https://github.com/LisaRebecca/Gruppe4.git

Jetzt kann man unser Projekt auch bei euch auf dem Rechner sehen:
cd Gruppe4
dir
Die beiden commands zeigen euch die Folder im Projekt an.

*** Hier einige Commands:***
in einen Branch wechseln:
git branch <new-branch-name>

in den master branch wechseln:
git branch master

Neuen Branch erstellen und auch in diesem Arbeiten:
git checkout -b new-branch-name
jetzt könnt ihr beliebig Dateien verändern (Im File explorer, Eclipse, Word, ...)

wenn ihr fertig seid committet es in euren Branch:
git add .
git commit -m "Beschreibung der Änderungen mit Anführungszeichen außenherum"

Jetzt sind die Änderungen aber nur auf eurem PC, wir wollen sie aber in der cloud:
git push origin your-branch-name

alle remote branches anzeigen:
git remote show origin

update all remotes:
git remote update --prune origin
