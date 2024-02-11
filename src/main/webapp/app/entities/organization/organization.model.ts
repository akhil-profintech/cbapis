import { IUserDtls } from 'app/entities/user-dtls/user-dtls.model';
import { ITradePartner } from 'app/entities/trade-partner/trade-partner.model';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';

export interface IOrganization {
  id: number;
  orgId?: string | null;
  orgName?: string | null;
  orgAddress?: string | null;
  industryType?: string | null;
  tenantId?: string | null;
  userDtls?: Pick<IUserDtls, 'id'>[] | null;
  tradePartners?: Pick<ITradePartner, 'id'>[] | null;
  anchorTraders?: Pick<IAnchorTrader, 'id'>[] | null;
  financePartners?: Pick<IFinancePartner, 'id'>[] | null;
}

export type NewOrganization = Omit<IOrganization, 'id'> & { id: null };
