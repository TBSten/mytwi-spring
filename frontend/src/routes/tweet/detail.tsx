import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import BaseLayout from '../../components/BaseLayout'
import { Tweet, TweetSchema } from '../../tweet/type'
import { User, UserSchema } from '../../auth/type'

export default function TweetDetailPage() {
    const { id } = useParams()
    const [tweet, setTweet] = useState<Tweet | null>(null)
    const [author, setAuthor] = useState<User | null>(null)
    useEffect(() => {
        (async () => {
            const tweet = await fetch(`/api/tweet/${id}`)
                .then(r => r.json())
                .then(r => TweetSchema.parse(r))
            setTweet(tweet)
            if (!tweet) return
            const author = await fetch(`/api/user/${tweet.authorId}`)
                .then(r => r.json())
                .then(r => UserSchema.parse(r))
            setAuthor(author)
        })()
    }, [])
    if (!tweet) {
        return <>
            ...
        </>
    }
    const createAt = new Date(tweet.createAt)
    return (
        <BaseLayout>
            <div className="p-2 font-bold">
                ツイート
            </div>
            <div className='border border-base-100 p-4 md:p-6'>
                <div className="mb-2 md:mb-4">
                    <span className='text-bold mr-2'>
                        {author?.name}
                    </span>
                    <span className='text-sm text-gray-700 '>
                        @{tweet.authorId}
                    </span>
                </div>
                <div className='break-all text-lg'>
                    {tweet.content}
                </div>
                <div className="my-2 text-gray-700 ">
                    <span className='mx-1'>
                        {createAt.getHours()}時
                        {createAt.getMinutes()}分
                    </span>
                    <span className='text-sm '>
                        {createAt.getFullYear()}年
                        {createAt.getMonth() + 1}月
                        {createAt.getDate()}日
                    </span>
                </div>
            </div>
        </BaseLayout>
    )
}
