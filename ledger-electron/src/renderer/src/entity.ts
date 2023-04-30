export interface AccountBalance {
  accountId: string
  commodity: string
  bookBalance: number
  totalInflow: number
  totalOutflow: number
}

export interface Account {
  accountId: string
  parentAccountId: string
  name: string
  icon: string
  majorCommodity: string
  accountBalance: Array<AccountBalance>
}

export interface AccountTree extends Account {
  children: Array<AccountTree>
}
