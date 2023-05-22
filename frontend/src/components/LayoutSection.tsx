import { ReactNode } from 'react'

export interface LayoutSectionProps {
    children?: ReactNode
}
export default function LayoutSection({ children }: LayoutSectionProps) {
    return (
        <div className='p-2 md:p-6 flex justify-center w-full'>
            <div className="max-w-5xl w-full">
                {children}
            </div>
        </div>
    )
}
