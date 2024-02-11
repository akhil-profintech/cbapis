import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IFinanceRequestMapping } from '../finance-request-mapping.model';

@Component({
  standalone: true,
  selector: 'jhi-finance-request-mapping-detail',
  templateUrl: './finance-request-mapping-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FinanceRequestMappingDetailComponent {
  @Input() financeRequestMapping: IFinanceRequestMapping | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
