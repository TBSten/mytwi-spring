import { useEffect, useState } from 'react'
import BaseLayout from '../../components/BaseLayout'
import { Link, useNavigate } from 'react-router-dom'
import { Tweet, TweetSchema } from '../../tweet/type'
import { User, UserSchema } from '../../auth/type'
import { z } from 'zod'
import { logout } from '../../auth/client'
import LayoutSection from '../../components/LayoutSection'

export default function TopPage() {
    const [tweets, setTweets] = useState<Tweet[]>([])
    const [users, setUsers] = useState<Record<string, User>>({})
    const [sessionUser, setSessionUser] = useState<User | null>(null)
    const refreshTweets = async () => {
        await fetch("/api/tweets/timeline").then(r => r.json())
            // .then(r => { console.log("tl", r); return r })
            .then(r => {
                setTweets(TweetSchema.array().parse(r?.tweets))
                setUsers(z.record(z.string(), UserSchema).parse(r?.users))
            })
    }
    useEffect(() => {
        refreshTweets()
        fetch("/api/user/session").then(r => r.json())
            .then(r => {
                // console.log(r);
                const user = UserSchema.safeParse(r)
                setSessionUser(user.success ? user.data : null)
            })
            .catch(console.error)
    }, [])

    const [editing, setEditing] = useState("")
    const handleTweet = async () => {
        const headers = new Headers()
        headers.append("Content-Type", "application/json")
        await fetch("/api/tweets", {
            method: "POST",
            headers,
            body: JSON.stringify({ content: editing, })
        }).then(r => r.json())
        setEditing("")
        refreshTweets()
    }
    const navigate = useNavigate()
    const handleLogout = async () => {
        await logout()
        navigate("/login")
    }
    const handleGotoDetail = (tweet: Tweet) => () => {
        navigate(`/tweet/${tweet.id}`)
    }
    return (
        <BaseLayout>
            <div className="p-2 flex justify-between">
                <span className='font-bold '>
                    Home
                </span>
                <div className='flex flex-wrap justify-end'>
                    {sessionUser
                        ? <>
                            {sessionUser.name}
                            {" "}としてログイン中
                            {" | "}
                            <button className='text-primary-100 hover:text-primary-200 cursor-pointer' onClick={handleLogout}>
                                ログアウト
                            </button>
                        </>
                        : <>
                            <Link to='/login' className='text-primary-100 hover:text-primary-200 cursor-pointer'>
                                ログイン
                            </Link>
                            または
                            <Link to='/signup' className='text-primary-100 hover:text-primary-200 cursor-pointer'>
                                サインアップ
                            </Link>
                        </>
                    }
                </div>

            </div>
            <LayoutSection>
                {sessionUser &&
                    <div className="border-x border-t border-base-100">
                        <div className="p-1">
                            <span className="font-bold">
                                {sessionUser.name}
                            </span>
                            としてログイン中
                        </div>
                        <div className='p-2 group'>
                            <textarea
                                className='p-2 pb-4 outline-none w-full text-lg'
                                placeholder='今何してる？'
                                value={editing}
                                onChange={e => setEditing(e.target.value)}
                            ></textarea>
                            <div className="flex justify-end">
                                <button
                                    className="bg-primary-100 text-white px-3 py-1 rounded-full hover:bg-primary-200 active:scale-95 transition"
                                    onClick={handleTweet}
                                >
                                    ツイート
                                </button>
                            </div>
                        </div>
                    </div>
                }
                <div className='border-b border-base-100'>
                    {tweets.map(tweet =>
                        <div
                            key={tweet.id}
                            className="block border border-b-0 border-base-100 p-2 md:p-4 hover:bg-gray-100 transition"
                            onClick={handleGotoDetail(tweet)}
                        >
                            <div className="text-sm text-gray-700">
                                {users[tweet.authorId].name}
                            </div>
                            <div className="p-2 break-all">
                                {tweet.content}
                            </div>
                        </div>
                    )}
                </div>
            </LayoutSection>
        </BaseLayout>
    )
}
