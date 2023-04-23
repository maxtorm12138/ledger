export interface Account {
    name : string,
    iconId : string
}

export interface AccountTree extends Account {
    children: Array<AccountTree>
}
