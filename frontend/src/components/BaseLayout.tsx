import { ReactNode } from 'react'

export interface BaseLayoutProps {
    children?: ReactNode
}

export default function BaseLayout({ children }: BaseLayoutProps) {
    return (
        <div>
            {children}
        </div>
    )
}
