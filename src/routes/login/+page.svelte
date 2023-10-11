<script>
    let email;
    let password;

    function handleSubmit() {
        console.log(`Email: ${email}, Password: ${password}`);
    }
</script>

<div>
    <h1 class="py-10 text-3xl text-center">Log in / Register</h1>
    <form on:submit|preventDefault={handleSubmit} class="flex flex-col items-center">
        <input type="email" placeholder="Email" bind:value={email} class="p-2 mb-2 border rounded" required />
        <input type="password" placeholder="Password" bind:value={password} class="p-2 mb-2 border rounded" required />
        <div class="p-2">
            <button type="submit" class="btn btn-neutral btn-xs">Log In</button>
            <button type="submit" class="btn btn-neutral btn-xs">Register</button>
        </div>
    </form>    
</div>