<?php include 'src/head.php'; include 'src/nav.php'; ?>
<section class="home">
    <section class="overall">

        <h1 class="pagetitle">Accueil</h1>
        <section class="timegraph">
            <!-- graphe du temps et de la conso -->
            <h2>Consommation de l'appartement</h2>
            <!-- bouton de controle de la piece ou prise -->
            <p class="onoff">
                <label title="onoff">Gestion générale</label>
                <input type="radio" name="onoff" value="On">
                <input type="radio" name="onoff" value="Off">
            </p>
            <!-- À afficher si l'état alwaysOn=true -->
            <p class="alwayson">Always On</p>
            <!-- graphe à mettre ici -->
            <article class="graph"></article>
        </section>
    </section>
    <section class="heatmap">
        <h2>Consommation par pièces</h2>
        <article class="map"></article>
    </section>
</section>
<?php include 'src/foot.php'; ?>