import { IOrganization, NewOrganization } from './organization.model';

export const sampleWithRequiredData: IOrganization = {
  id: 19724,
};

export const sampleWithPartialData: IOrganization = {
  id: 2052,
  orgId: 'trail likewise yet',
  orgName: 'following outrageous that',
};

export const sampleWithFullData: IOrganization = {
  id: 3012,
  orgId: 'reward owlishly',
  orgName: 'judgementally',
  orgAddress: 'adventurously climb before',
  industryType: 'yowza recap',
  tenantId: 'as pince-nez acclaimed',
};

export const sampleWithNewData: NewOrganization = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
