import { z } from "zod"

export const TweetSchema = z.object({
    id: z.number(),
    content: z.string(),
    createAt: z.string(),
    authorId: z.string(),
})
export type Tweet = z.infer<typeof TweetSchema>

