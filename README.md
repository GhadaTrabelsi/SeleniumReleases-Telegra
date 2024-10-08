# SeleniumReleases-TelegramBot
Selenium Releases Telegram Bot est un bot Telegram conçu pour récupérer les dernières informations sur les releases de Selenium à partir du site officiel et les envoyer aux utilisateurs via Telegram.

Fonctionnalités
Récupère automatiquement les dernières versions de Selenium depuis le site officiel.
Envoie des informations sur les releases de Selenium aux utilisateurs de Telegram.
Répond à la commande /start par un message de bienvenue.
Peut être personnalisé pour répondre à des messages spécifiques.
Prérequis
Java JDK 11 ou supérieur installé.
Eclipse IDE ou un autre IDE Java pour exécuter le projet.
Maven pour gérer les dépendances.
Un bot Telegram avec un token valide, généré via BotFather.
Installation
Clonez le projet :

bash
Copier le code
git clone https://github.com/GhadaTrabelsi/SeleniumReleases-TelegramBot.git

Configurez le bot Telegram :

Créez un bot avec BotFather sur Telegram.
Utilisez la commande /newbot pour générer un bot et obtenir un token.
Remplacez le token dans le fichier SeleniumNewsBot.java dans la méthode getBotToken().
Ajouter les dépendances Maven dans le fichier pom.xml :

xml
Copier le code
<dependencies>
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.14.3</version>
    </dependency>

    <dependency>
        <groupId>org.telegram</groupId>
        <artifactId>telegrambots</artifactId>
        <version>5.3.0</version>
    </dependency>
</dependencies>
Exécutez le projet :

Ouvrez SeleniumNewsBot.java et exécutez-le dans votre IDE.
Le message "Le bot est démarré!" devrait apparaître dans la console.

Tester sur Telegram :

Recherchez votre bot sur Telegram avec son nom d'utilisateur.
Envoyez la commande /start pour vérifier que le bot fonctionne.
Fonctionnement
Le bot utilise Jsoup pour scrapper le site officiel de Selenium et récupérer les informations des releases.
Il envoie ensuite ces informations via Telegram en fonction des messages reçus.

Méthode onUpdateReceived()
Répond à la commande /start par un message de bienvenue.
Pour tout autre message, renvoie les informations des dernières releases de Selenium.
Méthode getSeleniumReleaseInfo()
Cette méthode scrappe les informations de release à partir du site Selenium et retourne une chaîne contenant les versions de Selenium et le lien vers le changelog GitHub.


Contribuer

Les contributions sont les bienvenues ! Si vous avez des idées d'amélioration ou des correctifs, n'hésitez pas à soumettre des pull requests.
