import { Persona } from 'app/entities/enumerations/persona.model';

export interface IUserDtls {
  id: number;
  userUlidId?: string | null;
  userName?: string | null;
  tenantId?: string | null;
  isAnchorTraderEnabled?: boolean | null;
  anchorTraderId?: string | null;
  isTradePartnerEnabled?: boolean | null;
  tradePartnerId?: string | null;
  isFinancePartnerEnabled?: boolean | null;
  financePartnerId?: string | null;
  defaultPersona?: keyof typeof Persona | null;
}

export type NewUserDtls = Omit<IUserDtls, 'id'> & { id: null };
