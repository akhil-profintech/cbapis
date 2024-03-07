import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITradeEntity, NewTradeEntity } from '../trade-entity.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITradeEntity for edit and NewTradeEntityFormGroupInput for create.
 */
type TradeEntityFormGroupInput = ITradeEntity | PartialWithRequiredKeyOf<NewTradeEntity>;

type TradeEntityFormDefaults = Pick<NewTradeEntity, 'id'>;

type TradeEntityFormGroupContent = {
  id: FormControl<ITradeEntity['id'] | NewTradeEntity['id']>;
  entityId: FormControl<ITradeEntity['entityId']>;
  entityUlidId: FormControl<ITradeEntity['entityUlidId']>;
  entityName: FormControl<ITradeEntity['entityName']>;
  entityDesc: FormControl<ITradeEntity['entityDesc']>;
  entityGST: FormControl<ITradeEntity['entityGST']>;
};

export type TradeEntityFormGroup = FormGroup<TradeEntityFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TradeEntityFormService {
  createTradeEntityFormGroup(tradeEntity: TradeEntityFormGroupInput = { id: null }): TradeEntityFormGroup {
    const tradeEntityRawValue = {
      ...this.getFormDefaults(),
      ...tradeEntity,
    };
    return new FormGroup<TradeEntityFormGroupContent>({
      id: new FormControl(
        { value: tradeEntityRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      entityId: new FormControl(tradeEntityRawValue.entityId),
      entityUlidId: new FormControl(tradeEntityRawValue.entityUlidId),
      entityName: new FormControl(tradeEntityRawValue.entityName),
      entityDesc: new FormControl(tradeEntityRawValue.entityDesc),
      entityGST: new FormControl(tradeEntityRawValue.entityGST),
    });
  }

  getTradeEntity(form: TradeEntityFormGroup): ITradeEntity | NewTradeEntity {
    return form.getRawValue() as ITradeEntity | NewTradeEntity;
  }

  resetForm(form: TradeEntityFormGroup, tradeEntity: TradeEntityFormGroupInput): void {
    const tradeEntityRawValue = { ...this.getFormDefaults(), ...tradeEntity };
    form.reset(
      {
        ...tradeEntityRawValue,
        id: { value: tradeEntityRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TradeEntityFormDefaults {
    return {
      id: null,
    };
  }
}
