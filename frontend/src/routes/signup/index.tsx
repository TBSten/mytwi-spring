import { useState } from 'react'
import BaseLayout from '../../components/BaseLayout'
import PageTitle from '../../components/PageTitle'
import LayoutSection from '../../components/LayoutSection'
import { Link, useNavigate } from "react-router-dom"
import { singUp } from '../../auth/client'

export default function SignUpPage() {
    const [id, setId] = useState("")
    const [userName, setUserName] = useState("")
    const [password, setPassword] = useState("")
    const [status, setStatus] = useState<"before" | "loading" | "success" | "error">("before")
    const navigate = useNavigate()
    const handleSignUp = async () => {
        try {
            setStatus("loading")
            await singUp(id, userName, password)
            setStatus("success")
            navigate("/")
        } catch (error) {
            console.error(error)
            setStatus("error")
        }
    }
    return (
        <BaseLayout>
            <PageTitle className='text-center'>
                サインアップ
            </PageTitle>
            <LayoutSection>
                <div className="my-2 text-center flex flex-col items-center gap-4">
                    <input
                        className='border border-base-100 rounded text-xl'
                        type="text"
                        placeholder='ユーザID'
                        value={id} onChange={e => setId(e.target.value)}
                    />
                    <input
                        className='border border-base-100 rounded text-xl'
                        type="text"
                        placeholder='ユーザ名'
                        value={userName} onChange={e => setUserName(e.target.value)}
                    />
                    <input
                        className='border border-base-100 rounded text-xl'
                        type="text"
                        placeholder='パスワード'
                        value={password} onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <div className="my-2 flex justify-center">
                    <button
                        className="bg-primary-100 text-white p-2 rounded hover:bg-primary-200 active:scale-95 transition"
                        onClick={handleSignUp}
                    >
                        サインアップ
                    </button>
                </div>
                <div className="text-center mt-12">
                    {status === "error" &&
                        <div className="my-2 p-4 bg-red-500 text-white rounded">
                            エラーが発生しました。
                            ログイン情報を確認し、もう一度ログインしてください。
                        </div>
                    }
                    {status === "loading" &&
                        <div className='my-2'>
                            ...
                        </div>
                    }
                    <div className="my-2">
                        すでにアカウントをお持ちの場合は
                        <Link to="/login" className='text-primary-100 hover:text-primary-200'>
                            ログイン
                        </Link>
                    </div>
                </div>
            </LayoutSection>
        </BaseLayout>
    )
}
