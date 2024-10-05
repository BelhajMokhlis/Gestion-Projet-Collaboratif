# Gestion-Projet-Collaboratif

## Description du projet
Gestion-Projet-Collaboratif est une application web Java EE conçue pour faciliter la gestion de projets en équipe. Elle permet aux utilisateurs de collaborer efficacement, de suivre l'avancement des tâches et de gérer les ressources du projet.

### Objectif général de l'application
L'objectif principal est d'offrir une plateforme centralisée pour la gestion de projets, améliorant la communication entre les membres de l'équipe et optimisant le suivi des tâches et des délais.

## Technologies utilisées
- **Backend**: Java EE
- **Frontend**: HTML, CSS, JavaScript, JSP
- **Base de données**: MySQL
- **Serveur d'application**: Apache Tomcat
- **Frameworks**: Bootstrap 4.5.2
- **Autres**: JSTL, Font Awesome

## Structure du projet
Le projet est organisé selon une architecture MVC (Modèle-Vue-Contrôleur) :
- `src/main/java`: Contient les classes Java (modèles, servlets, services)
- `src/main/webapp`: Contient les fichiers web (JSP, CSS, JavaScript)
- `src/main/resources`: Contient les fichiers de configuration

## Fonctionnalités principales
1. Gestion des projets (création, modification, suppression, visualisation)
2. Gestion des tâches (ajout, édition, liste par projet)
3. Gestion des équipes
4. Statistiques des projets

## Démarrage rapide

### Prérequis
- JDK 8 ou supérieur
- Apache Tomcat 9.0 ou supérieur
- MySQL 8.0 ou supérieur
- Maven (pour la gestion des dépendances)

### Configuration de la base de données
1. Créez une nouvelle base de données MySQL nommée `projectmanagement`
2. Exécutez le script SQL fourni dans `database/init.sql` pour créer les tables nécessaires

### Lancement de l'application
1. Clonez le dépôt :
   ```
   git clone https://github.com/BelhajMokhlis/Gestion-Projet-Collaboratif
   ```
2. Naviguez vers le répertoire du projet :
   ```
   cd Lamia-Mokhlis-Mostafa-S2-B1
   ```
3. Compilez le projet :
   ```
   mvn clean install
   ```
4. Déployez le fichier WAR généré sur votre serveur Tomcat
5. Accédez à l'application via `http://localhost:8080/Lamia-Mokhlis-Mostafa-S2-B1`

## Guide d'utilisation rapide
1. **Page d'accueil** : Choisissez entre la gestion des équipes, des projets ou des tâches
2. **Gestion des projets** : Créez, modifiez, supprimez et visualisez les projets
3. **Gestion des tâches** : Ajoutez et modifiez des tâches pour chaque projet
4. **Gestion des équipes** : Créez, modifiez, supprimez et visualisez les équipes et les membres de l'équipe

## Structure de la base de données

```sql
create database projectmanagement

-- Table for 'Team'
CREATE TABLE Team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Table for 'Member'
CREATE TABLE Member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role ENUM('project_manager', 'developer', 'designer') NOT NULL,
    team_id INT REFERENCES Team(id) on delete cascade
);

-- Table for 'Project'
CREATE TABLE Project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    startDate DATE NOT NULL,
    endDate DATE,
    status ENUM('in_preparation', 'in_progress', 'on_hold', 'completed', 'canceled') NOT NULL,
    team_id INT REFERENCES Team(id) ON DELETE SET NULL
);

-- Table for 'Task'
CREATE TABLE Task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    priority ENUM('low', 'medium', 'high') NOT NULL,
    status ENUM('to_do', 'in_progress', 'completed') NOT NULL,
    creationDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    project_id INT REFERENCES Project(id) ON DELETE CASCADE,
    member_id INT REFERENCES Member(id) ON DELETE SET NULL
);

-- Indexes
CREATE INDEX idx_project_name ON Project(name);
CREATE INDEX idx_task_project ON Task(project_id);
CREATE INDEX idx_member_email ON Member(email);

-- create user
CREATE USER 'user_app'@'localhost' IDENTIFIED BY '12345';

-- Grant Privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON projectmanagement.* TO 'user_app'@'localhost';

-- Apply the changes
FLUSH PRIVILEGES;

```

## Captures d'écran


### Page d'accueil
![Page d'accueil](https://raw.githubusercontent.com/BelhajMokhlis/Gestion-Projet-Collaboratif/refs/heads/main/captures/Home.png)

### Liste des projets
![Liste des projets](https://raw.githubusercontent.com/BelhajMokhlis/Gestion-Projet-Collaboratif/refs/heads/main/captures/ProjectList.png)

### Tâches du projet
![Tâches du projet](https://raw.githubusercontent.com/BelhajMokhlis/Gestion-Projet-Collaboratif/refs/heads/main/captures/ProjectTasks.png)

### Liste des équipes
![Liste des équipes](https://raw.githubusercontent.com/BelhajMokhlis/Gestion-Projet-Collaboratif/refs/heads/main/captures/TeamsList.png)

## Améliorations futures possibles
- Ajout d'un tableau de bord personnalisable
- Intégration de notifications en temps réel
- Mise en place d'un système de reporting avancé
- Ajout de fonctionnalités de gestion des ressources

## Auteurs et contact
Ce projet a été développé par une équipe d'étudiants :

- [Lamia](https://github.com/TERMOUSSI-LAMIAA)
- [Mokhlis](https://github.com/BelhajMokhlis)
- [Mostafa](https://github.com/MesVortex)

## Liens
- [Dépôt GitHub](https://github.com/BelhajMokhlis/Gestion-Projet-Collaboratif)


