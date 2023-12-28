import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDocDetails, NewDocDetails } from '../doc-details.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDocDetails for edit and NewDocDetailsFormGroupInput for create.
 */
type DocDetailsFormGroupInput = IDocDetails | PartialWithRequiredKeyOf<NewDocDetails>;

type DocDetailsFormDefaults = Pick<NewDocDetails, 'id'>;

type DocDetailsFormGroupContent = {
  id: FormControl<IDocDetails['id'] | NewDocDetails['id']>;
  docDetailsId: FormControl<IDocDetails['docDetailsId']>;
  docRecordId: FormControl<IDocDetails['docRecordId']>;
  link: FormControl<IDocDetails['link']>;
  description: FormControl<IDocDetails['description']>;
  docType: FormControl<IDocDetails['docType']>;
  status: FormControl<IDocDetails['status']>;
  disbursement: FormControl<IDocDetails['disbursement']>;
  repayment: FormControl<IDocDetails['repayment']>;
  participantsettlement: FormControl<IDocDetails['participantsettlement']>;
};

export type DocDetailsFormGroup = FormGroup<DocDetailsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DocDetailsFormService {
  createDocDetailsFormGroup(docDetails: DocDetailsFormGroupInput = { id: null }): DocDetailsFormGroup {
    const docDetailsRawValue = {
      ...this.getFormDefaults(),
      ...docDetails,
    };
    return new FormGroup<DocDetailsFormGroupContent>({
      id: new FormControl(
        { value: docDetailsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      docDetailsId: new FormControl(docDetailsRawValue.docDetailsId, {
        validators: [Validators.required],
      }),
      docRecordId: new FormControl(docDetailsRawValue.docRecordId, {
        validators: [Validators.required],
      }),
      link: new FormControl(docDetailsRawValue.link, {
        validators: [Validators.required],
      }),
      description: new FormControl(docDetailsRawValue.description, {
        validators: [Validators.required],
      }),
      docType: new FormControl(docDetailsRawValue.docType, {
        validators: [Validators.required],
      }),
      status: new FormControl(docDetailsRawValue.status, {
        validators: [Validators.required],
      }),
      disbursement: new FormControl(docDetailsRawValue.disbursement),
      repayment: new FormControl(docDetailsRawValue.repayment),
      participantsettlement: new FormControl(docDetailsRawValue.participantsettlement),
    });
  }

  getDocDetails(form: DocDetailsFormGroup): IDocDetails | NewDocDetails {
    return form.getRawValue() as IDocDetails | NewDocDetails;
  }

  resetForm(form: DocDetailsFormGroup, docDetails: DocDetailsFormGroupInput): void {
    const docDetailsRawValue = { ...this.getFormDefaults(), ...docDetails };
    form.reset(
      {
        ...docDetailsRawValue,
        id: { value: docDetailsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): DocDetailsFormDefaults {
    return {
      id: null,
    };
  }
}
