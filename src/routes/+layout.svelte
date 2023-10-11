<script>
    import "../app.css";
    import Navbar from "../components/UI/Navbar.svelte";
    import Footer from "../components/UI/Footer.svelte";
</script>

<Navbar />
<slot />


<style global lang="postcss">
    :global(:root) {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    :global(html, body) {
        font-family: 'Prompt', sans-serif;
    }
</style>
