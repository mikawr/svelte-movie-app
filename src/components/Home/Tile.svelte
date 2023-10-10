<script>
    export let title;
    export let year;
    export let rated;
    export let released;
    export let genre;
    export let poster;
</script>

<div class="w-96 rounded border border-4 border-primary p-5 indicator flex justify-between">
    <div class="flex flex-col pr-2">
        <span class="indicator-item badge badge-primary mr-10">{year}</span> 
        <h1 class="text-xl font-semibold tracking-wider">{title}</h1>
        <div>
            Rating: <div class="badge badge-outline badge-xs">{rated}</div>
            <br>
            Released: {released}
            <br>
            Genre: {genre}
        </div>
    </div>
    <img src={poster} alt={title} class="h-48 w-32 rounded">
</div>