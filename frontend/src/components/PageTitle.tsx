import classNames from 'classnames'
import { ReactNode } from 'react'

export interface PageTitleProps {
    children?: ReactNode
    className?: string
}
export default function PageTitle({ children, className }: PageTitleProps) {
    return (
        <h1 className={classNames(
            'my-8 font-bold text-5xl',
            className,
        )}>
            {children}
        </h1>
    )
}
