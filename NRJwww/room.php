<?php include '../src/header.php'; include '../src/nav.php'; ?>
<section class="room">
    <section class="overall">
        <h1 class="pagetitle">Chambre à coucher</h1>
        <section class="timegraph">
            <!-- graphe du temps et de la conso -->
            <h2>Consommation de la pièce</h2>
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
    <section class="plug">
        <section class="timegraph">
            <!-- graphe du temps et de la conso -->
            <h2>Consommation de la pièce</h2>
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
</section>
<?php include '../src/foot.php'; ?>