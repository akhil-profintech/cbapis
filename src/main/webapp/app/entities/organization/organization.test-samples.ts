import { IOrganization, NewOrganization } from './organization.model';

export const sampleWithRequiredData: IOrganization = {
  id: 10326,
};

export const sampleWithPartialData: IOrganization = {
  id: 22106,
  orgAddress: 'submarine trail',
  industryType: 'whoever while following',
};

export const sampleWithFullData: IOrganization = {
  id: 16244,
  orgId: 13127,
  orgUlidId: 'conscious tripod',
  orgName: 'woefully',
  orgAddress: 'oof tricky',
  industryType: 'who gee',
  tenantId: 'barley',
};

export const sampleWithNewData: NewOrganization = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
