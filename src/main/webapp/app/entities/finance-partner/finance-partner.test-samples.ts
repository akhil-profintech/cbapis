import { IFinancePartner, NewFinancePartner } from './finance-partner.model';

export const sampleWithRequiredData: IFinancePartner = {
  id: 7840,
  tenantId: 'unwitting underline haemorrhage',
  fpId: 'farrow',
  orgId: 'blah',
  customerName: 'possible under',
  orgName: 'concerning',
  gstId: 'dandelion shrine',
  phoneNumber: 22252,
};

export const sampleWithPartialData: IFinancePartner = {
  id: 26004,
  tenantId: 'boohoo',
  fpId: 'ouch',
  orgId: 'green',
  customerName: 'gee',
  orgName: 'ha unloosen shakily',
  gstId: 'yowza',
  phoneNumber: 21396,
};

export const sampleWithFullData: IFinancePartner = {
  id: 19366,
  tenantId: 'blister excitedly ew',
  fpId: 'daub meh yum',
  orgId: 'times',
  customerName: 'blond',
  orgName: 'amongst',
  gstId: 'prize unless oh',
  phoneNumber: 12040,
};

export const sampleWithNewData: NewFinancePartner = {
  tenantId: 'a',
  fpId: 'nicely inasmuch',
  orgId: 'serious',
  customerName: 'vastly',
  orgName: 'option',
  gstId: 'tame',
  phoneNumber: 1352,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
