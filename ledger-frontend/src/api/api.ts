import axios from "axios";

const service = axios.create({
})

export interface account {
    name: string,
    iconId: string
}

export interface accountTree {
    account: account
    children: Array<accountTree>
}
export async function portalAccountTree() {
    const accountTree: accountTree = (await service.get('/api/portal/tree')).data.data.accountTree;
    return accountTree;
}