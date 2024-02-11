import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFinanceRequestMapping, NewFinanceRequestMapping } from '../finance-request-mapping.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFinanceRequestMapping for edit and NewFinanceRequestMappingFormGroupInput for create.
 */
type FinanceRequestMappingFormGroupInput = IFinanceRequestMapping | PartialWithRequiredKeyOf<NewFinanceRequestMapping>;

type FinanceRequestMappingFormDefaults = Pick<NewFinanceRequestMapping, 'id'>;

type FinanceRequestMappingFormGroupContent = {
  id: FormControl<IFinanceRequestMapping['id'] | NewFinanceRequestMapping['id']>;
  financeRequestId: FormControl<IFinanceRequestMapping['financeRequestId']>;
  anchorTraderId: FormControl<IFinanceRequestMapping['anchorTraderId']>;
  financePartnerId: FormControl<IFinanceRequestMapping['financePartnerId']>;
  anchorTraderTenantId: FormControl<IFinanceRequestMapping['anchorTraderTenantId']>;
  financePartnerTenantId: FormControl<IFinanceRequestMapping['financePartnerTenantId']>;
};

export type FinanceRequestMappingFormGroup = FormGroup<FinanceRequestMappingFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FinanceRequestMappingFormService {
  createFinanceRequestMappingFormGroup(
    financeRequestMapping: FinanceRequestMappingFormGroupInput = { id: null },
  ): FinanceRequestMappingFormGroup {
    const financeRequestMappingRawValue = {
      ...this.getFormDefaults(),
      ...financeRequestMapping,
    };
    return new FormGroup<FinanceRequestMappingFormGroupContent>({
      id: new FormControl(
        { value: financeRequestMappingRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      financeRequestId: new FormControl(financeRequestMappingRawValue.financeRequestId),
      anchorTraderId: new FormControl(financeRequestMappingRawValue.anchorTraderId),
      financePartnerId: new FormControl(financeRequestMappingRawValue.financePartnerId),
      anchorTraderTenantId: new FormControl(financeRequestMappingRawValue.anchorTraderTenantId),
      financePartnerTenantId: new FormControl(financeRequestMappingRawValue.financePartnerTenantId),
    });
  }

  getFinanceRequestMapping(form: FinanceRequestMappingFormGroup): IFinanceRequestMapping | NewFinanceRequestMapping {
    return form.getRawValue() as IFinanceRequestMapping | NewFinanceRequestMapping;
  }

  resetForm(form: FinanceRequestMappingFormGroup, financeRequestMapping: FinanceRequestMappingFormGroupInput): void {
    const financeRequestMappingRawValue = { ...this.getFormDefaults(), ...financeRequestMapping };
    form.reset(
      {
        ...financeRequestMappingRawValue,
        id: { value: financeRequestMappingRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FinanceRequestMappingFormDefaults {
    return {
      id: null,
    };
  }
}
