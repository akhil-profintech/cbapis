import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICreditBazaarIntegrator } from '../credit-bazaar-integrator.model';

@Component({
  standalone: true,
  selector: 'jhi-credit-bazaar-integrator-detail',
  templateUrl: './credit-bazaar-integrator-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CreditBazaarIntegratorDetailComponent {
  @Input() creditBazaarIntegrator: ICreditBazaarIntegrator | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
