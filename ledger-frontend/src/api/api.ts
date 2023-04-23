import axios from "axios";
import {AccountTree} from "./entity";

const service = axios.create({
})


class Api {
    async accountTree() {
        const accountTree: AccountTree = ((await service.get('/api/account/tree')).data.data.accountTree);
        return accountTree;
    }
}

export const api = new Api();