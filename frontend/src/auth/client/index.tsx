import { UserSchema } from "../type"

export const singUp = async (id: string, userName: string, password: string) => {
    const headers = new Headers()
    headers.append("Content-Type", "application/json")
    const newUser =
        await fetch("/api/signup", {
            method: "POST",
            headers,
            body: JSON.stringify({ id, userName, password }),
        })
            .then(r => r.json())
            .then(r => UserSchema.parse(r))
    return newUser
}

export const login = async (id: string, password: string) => {
    const user = await fetch("/api/login", {
        method: "POST",
        headers: new Headers({
            "Content-Type": "application/json",
        }),
        body: JSON.stringify({ id, password }),
    })
        .then(r => r.json())
        .then(r => UserSchema.parse(r))
    return user
}

export const logout = async () => {
    await fetch("/api/logout", {
        method: "POST",
    }).then(r => r.json())
}
