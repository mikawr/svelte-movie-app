<script lang="ts">
    let errors: { [inputName: string]: any } = {};

    function isFormValid(data: { [inputName: string]: any }): boolean {
        return !Object.keys(errors).some((inputName) =>
            Object.keys(errors[inputName]).some(
                (errorName) => errors[inputName][errorName]
            )
        );
    }

    function validateForm(data: { [inputName: string]: any }): void {
        if (!isRequiredFieldValid(data.email)) {
            errors["email"] = { ...errors["email"], required: true };
        } else {
            errors["email"] = { ...errors["email"], required: false };
        }

        if (!isRequiredFieldValid(data.password)) {
            errors["password"] = { ...errors["password"], required: true };
        } else {
            errors["password"] = { ...errors["password"], required: false };
        }
    }

    function isRequiredFieldValid(value) {
        return value != null && value !== "";
    }

    function onSubmit(e) {
        const formData = new FormData(e.target);

        const data: any = {};
        for (let field of formData) {
            const [key, value] = field;
            data[key] = value;
        }

        validateForm(data);

        if (isFormValid(data)) {
            console.log(data);
        } else {
            console.log("Invalid Form");
        }
    }
</script>

<main class="flex justify-center items-center h-screen">
    <form
        on:submit|preventDefault={onSubmit}
        class="flex flex-col gap-5 p-5 bg-primary text-base-100 rounded-md [&>div>input]:rounded [&>div>input]:p-2"
    >
        <div class="text-neutral">
            <input
                type="text"
                id="email"
                name="email"
                value=""
                placeholder="Email"
                autocomplete="email"
            />
            {#if errors.email && errors.email.required}
                <p class="error-message">Email is required</p>
            {/if}
        </div>
        <div class="text-neutral">
            <input
                type="password"
                id="password"
                name="password"
                value=""
                placeholder="Password"
            />
            {#if errors.password && errors.password.required}
                <p class="error-message">Password is required</p>
            {/if}
        </div>
        <button class="btn btn-neutral p-2 rounded cursor-pointer" type="submit"
            >Submit</button
        >
    </form>
</main>

<style>
    .error-message {
        color: tomato;
        flex: 0 0 100%;
        margin: 0 2px;
        font-size: 0.8em;
    }
</style>
