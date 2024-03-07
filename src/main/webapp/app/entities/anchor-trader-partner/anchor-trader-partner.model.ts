import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';

export interface IAnchorTraderPartner {
  id: number;
  atpartnerId?: number | null;
  atpartnerUlidId?: string | null;
  pan?: string | null;
  panStatus?: string | null;
  name?: string | null;
  aadhar?: string | null;
  aadharOtp?: string | null;
  aadharStatus?: string | null;
  aadharName?: string | null;
  aadharAddress?: string | null;
  anchortrader?: Pick<IAnchorTrader, 'id' | 'atId'> | null;
}

export type NewAnchorTraderPartner = Omit<IAnchorTraderPartner, 'id'> & { id: null };
