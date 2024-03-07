import { IFinancePartner, NewFinancePartner } from './finance-partner.model';

export const sampleWithRequiredData: IFinancePartner = {
  id: 30469,
  tenantId: 'too aside',
  orgId: 'disown unless insect',
  customerName: 'blaring decriminalise',
  orgName: 'gah',
  gstId: 'meanwhile',
  phoneNumber: 3301,
};

export const sampleWithPartialData: IFinancePartner = {
  id: 21058,
  tenantId: 'boo voluntarily towards',
  orgId: 'bunker',
  customerName: 'tomorrow scrutinise',
  orgName: 'seed',
  gstId: 'margarine putt blister',
  phoneNumber: 3372,
  tosDocument: 'instead mmm bitterly',
};

export const sampleWithFullData: IFinancePartner = {
  id: 2134,
  fpId: 6752,
  fpUlidId: 'neglect picturize fox',
  tenantId: 'offensively while',
  orgId: 'since frantically',
  customerName: 'dramatize geology nicely',
  orgName: 'pfft serious woot',
  gstId: 'mmm while heart',
  phoneNumber: 237,
  tosDocument: 'practical tally large',
};

export const sampleWithNewData: NewFinancePartner = {
  tenantId: 'and miserably',
  orgId: 'gosh gum',
  customerName: 'reproachfully lucky mortified',
  orgName: 'insurance coruscate elastic',
  gstId: 'over jovially whereas',
  phoneNumber: 1348,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
