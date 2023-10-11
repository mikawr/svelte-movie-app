<script>
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
    
    import Tile from "./Tile.svelte";

    let ids = ["tt0111161", "tt0068646", "tt0071562", "tt0468569"]
    let moviesStore = writable([])
    let loadedStore = writable(false);

    const APIKEY = "a9e62929";
    const getMovieData = async (id) => {
        return await fetch(`https://www.omdbapi.com/?i=${id}&apikey=${APIKEY}`)
            .then(res => res.json()
            .then(data => data)
        );
    }

    let isLoading = true;
    onMount(async () => {
        let movies = [];
        for (let i=0; i<ids.length; i++) {
            movies.push(await getMovieData(ids[i]));
        }

        moviesStore.set(movies);
        loadedStore.set(true);
    });
</script>

<div class="py-10">
    <h1 class="text-xl text-center">Newly added movies</h1>

    <div class="w-full py-10 flex flex-row justify-center flex-wrap gap-4">
        {#if !$loadedStore}
            <span class="loading loading-spinner loading-lg"></span>
        {:else}
            {#each $moviesStore as movie}
                <Tile 
                    title={movie.Title} 
                    year={movie.Year}
                    rated={movie.Rated}
                    released={movie.Released}
                    genre={movie.Genre}
                    poster={movie.Poster}
                />
            {/each}
        {/if}
    </div>
</div>