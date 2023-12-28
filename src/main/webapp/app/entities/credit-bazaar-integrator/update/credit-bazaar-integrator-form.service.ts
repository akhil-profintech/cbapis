import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreditBazaarIntegrator, NewCreditBazaarIntegrator } from '../credit-bazaar-integrator.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreditBazaarIntegrator for edit and NewCreditBazaarIntegratorFormGroupInput for create.
 */
type CreditBazaarIntegratorFormGroupInput = ICreditBazaarIntegrator | PartialWithRequiredKeyOf<NewCreditBazaarIntegrator>;

type CreditBazaarIntegratorFormDefaults = Pick<NewCreditBazaarIntegrator, 'id'>;

type CreditBazaarIntegratorFormGroupContent = {
  id: FormControl<ICreditBazaarIntegrator['id'] | NewCreditBazaarIntegrator['id']>;
  tenantId: FormControl<ICreditBazaarIntegrator['tenantId']>;
  integratorId: FormControl<ICreditBazaarIntegrator['integratorId']>;
  orgId: FormControl<ICreditBazaarIntegrator['orgId']>;
  customerName: FormControl<ICreditBazaarIntegrator['customerName']>;
  orgName: FormControl<ICreditBazaarIntegrator['orgName']>;
  gstId: FormControl<ICreditBazaarIntegrator['gstId']>;
  phoneNumber: FormControl<ICreditBazaarIntegrator['phoneNumber']>;
};

export type CreditBazaarIntegratorFormGroup = FormGroup<CreditBazaarIntegratorFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreditBazaarIntegratorFormService {
  createCreditBazaarIntegratorFormGroup(
    creditBazaarIntegrator: CreditBazaarIntegratorFormGroupInput = { id: null },
  ): CreditBazaarIntegratorFormGroup {
    const creditBazaarIntegratorRawValue = {
      ...this.getFormDefaults(),
      ...creditBazaarIntegrator,
    };
    return new FormGroup<CreditBazaarIntegratorFormGroupContent>({
      id: new FormControl(
        { value: creditBazaarIntegratorRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tenantId: new FormControl(creditBazaarIntegratorRawValue.tenantId, {
        validators: [Validators.required],
      }),
      integratorId: new FormControl(creditBazaarIntegratorRawValue.integratorId, {
        validators: [Validators.required],
      }),
      orgId: new FormControl(creditBazaarIntegratorRawValue.orgId, {
        validators: [Validators.required],
      }),
      customerName: new FormControl(creditBazaarIntegratorRawValue.customerName, {
        validators: [Validators.required],
      }),
      orgName: new FormControl(creditBazaarIntegratorRawValue.orgName, {
        validators: [Validators.required],
      }),
      gstId: new FormControl(creditBazaarIntegratorRawValue.gstId, {
        validators: [Validators.required],
      }),
      phoneNumber: new FormControl(creditBazaarIntegratorRawValue.phoneNumber, {
        validators: [Validators.required],
      }),
    });
  }

  getCreditBazaarIntegrator(form: CreditBazaarIntegratorFormGroup): ICreditBazaarIntegrator | NewCreditBazaarIntegrator {
    return form.getRawValue() as ICreditBazaarIntegrator | NewCreditBazaarIntegrator;
  }

  resetForm(form: CreditBazaarIntegratorFormGroup, creditBazaarIntegrator: CreditBazaarIntegratorFormGroupInput): void {
    const creditBazaarIntegratorRawValue = { ...this.getFormDefaults(), ...creditBazaarIntegrator };
    form.reset(
      {
        ...creditBazaarIntegratorRawValue,
        id: { value: creditBazaarIntegratorRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CreditBazaarIntegratorFormDefaults {
    return {
      id: null,
    };
  }
}
