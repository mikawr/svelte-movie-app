<script>
    import axios from "axios";
    import { goto } from "$app/navigation";
    import { writable } from "svelte/store";

    let name;
    let email;
    let password;
    let showError = writable(false);

    $:handleSubmit = async () => {
        await axios.post("http://localhost:8080/api/v1/users/register", {
            name,
            email,
            password
        }).then((res) => {
            if (res.status == 200) {
                goto("/login")
            }
        }).catch((error) => {
            showError.set(true);
        });
    }
</script>

<div>
    <h1 class="pt-20 pb-5 text-2xl text-center">Sign up your account</h1>
    {#if $showError}
        <p class="text-center pb-5">
            <span class="text-red-500">Username or Email already taken. Please try again.</span>
        </p>
    {/if}
    <form
        on:submit|preventDefault={handleSubmit}
        class="flex flex-col items-center"
    >
        <input
            type="text"
            placeholder="Name"
            bind:value={name}
            class="p-2 mb-2 border rounded"
            required
        />
        <input
            type="email"
            placeholder="Email"
            bind:value={email}
            class="p-2 mb-2 border rounded"
            required
        />
        <input
            type="password"
            placeholder="Password"
            bind:value={password}
            class="p-2 mb-2 border rounded"
            required
        />
        <div class="pt-5">
            <button type="submit" class="btn btn-neutral btn-xs"
                >Sign Up</button
            >
        </div>
    </form>
</div>
