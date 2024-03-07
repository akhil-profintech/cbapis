import { IAction, NewAction } from './action.model';

export const sampleWithRequiredData: IAction = {
  id: 32505,
};

export const sampleWithPartialData: IAction = {
  id: 17494,
  actionVal: 'athwart',
  cpCode: 'supposing intentional forenenst',
};

export const sampleWithFullData: IAction = {
  id: 12909,
  actionId: 'flaunt yippee',
  actionCode: 'allow reluctantly',
  actionDescription: 'capital hippodrome',
  actionVal: 'oh',
  cpCode: 'woot fragrant pressurization',
};

export const sampleWithNewData: NewAction = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
